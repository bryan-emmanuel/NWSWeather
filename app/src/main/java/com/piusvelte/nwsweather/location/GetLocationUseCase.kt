package com.piusvelte.nwsweather.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.piusvelte.nwsweather.permission.PermissionGrantUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.asDeferred
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GetLocationUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    permissionGrantUseCase: PermissionGrantUseCase,
) {

    private val permissionCheck: Flow<Boolean> = flow {
        val grant = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )

        emit(grant == PackageManager.PERMISSION_GRANTED)
    }

    private val permissionGrant: Flow<Boolean> = permissionGrantUseCase.permissionGrant()
        .map { it == Manifest.permission.ACCESS_COARSE_LOCATION }

    private val permissionGranted: Flow<Boolean> = merge(permissionCheck, permissionGrant)
        .distinctUntilChanged()

    @SuppressLint("MissingPermission")
    private fun lastLocation(client: FusedLocationProviderClient) = flow {
        val location = client.lastLocation.asDeferred().await()
        emit(location)
    }

    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    private fun locationUpdates(client: FusedLocationProviderClient) = callbackFlow {
        val request = LocationRequest.create().apply {
            interval = TimeUnit.MINUTES.toMillis(1)
            fastestInterval = TimeUnit.SECONDS.toMillis(30)
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                result.lastLocation?.run {
                    trySendBlocking(this)
                }
            }
        }

        client.requestLocationUpdates(request, callback, Looper.getMainLooper())

        awaitClose { client.removeLocationUpdates(callback) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<Location> {
        return permissionGranted.flatMapLatest {
            if (it) {
                val client = LocationServices.getFusedLocationProviderClient(context)
                merge(lastLocation(client), locationUpdates(client))
            } else {
                emptyFlow()
            }
        }.filterNotNull()
    }
}
