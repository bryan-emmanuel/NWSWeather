package com.piusvelte.nwsweather.data.dto

sealed class ResourceDto<out T : Any> {
    data object Loading : ResourceDto<Nothing>()
    data class Error(val exception: Throwable) : ResourceDto<Nothing>()
    data class Success<out T : Any>(val data: T) : ResourceDto<T>()
}
