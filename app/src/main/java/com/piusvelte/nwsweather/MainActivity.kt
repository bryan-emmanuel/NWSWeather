package com.piusvelte.nwsweather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var permissionsLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }

        permissionsLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {
                    viewModel.onPermissionGrant(Manifest.permission.ACCESS_COARSE_LOCATION)
                }
            }

        checkLocationPermission()
    }

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
