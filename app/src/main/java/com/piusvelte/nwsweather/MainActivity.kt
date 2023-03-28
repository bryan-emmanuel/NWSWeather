package com.piusvelte.nwsweather

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.piusvelte.nwsweather.di.FragmentFactoryInstaller
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var installer: FragmentFactoryInstaller

    private val viewModel: MainViewModel by viewModels()

    private lateinit var permissionsLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionsLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {
                    viewModel.onPermissionGrant(Manifest.permission.ACCESS_COARSE_LOCATION)
                }
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) checkLocationPermission()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkLocationPermission() {
        val grant =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        when {
            grant == PackageManager.PERMISSION_GRANTED -> {
                viewModel.onPermissionGrant(Manifest.permission.ACCESS_COARSE_LOCATION)
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                //TODO
                // rationale
            }

            else -> permissionsLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
}
