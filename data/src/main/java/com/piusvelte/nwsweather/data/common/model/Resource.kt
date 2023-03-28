package com.piusvelte.nwsweather.data.common.model

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
}
