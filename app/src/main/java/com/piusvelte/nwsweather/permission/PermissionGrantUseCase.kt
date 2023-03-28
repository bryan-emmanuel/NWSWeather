package com.piusvelte.nwsweather.permission

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PermissionGrantUseCase @Inject constructor() {

    private val permissionGrant: MutableSharedFlow<String> = MutableSharedFlow()

    suspend fun onPermissionGrant(permission: String) {
        permissionGrant.emit(permission)
    }

    fun permissionGrant(): Flow<String> = permissionGrant
}
