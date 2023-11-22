package com.piusvelte.nwsweather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piusvelte.nwsweather.permission.PermissionGrantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val permissionGrantUseCase: PermissionGrantUseCase,
    private val handle: SavedStateHandle,
) : ViewModel() {

    private val permission = handle.getStateFlow(PERMISSION, "")

    init {
        viewModelScope.launch {
            permission
                .filter { it.isNotEmpty() }
                .flatMapLatest {
                    flow<Unit> {
                        permissionGrantUseCase.onPermissionGrant(it)
                    }
                }
        }
    }

    fun onPermissionGrant(permission: String) {
        handle[PERMISSION] = permission
    }

    private companion object {
        private const val PERMISSION = "state:permission"
    }
}
