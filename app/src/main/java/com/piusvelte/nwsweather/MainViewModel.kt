package com.piusvelte.nwsweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusvelte.nwsweather.permission.PermissionGrantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val permissionGrantUseCase: PermissionGrantUseCase,
) : ViewModel() {
    fun onPermissionGrant(permission: String) {
        viewModelScope.launch {
            permissionGrantUseCase.onPermissionGrant(permission)
        }
    }
}
