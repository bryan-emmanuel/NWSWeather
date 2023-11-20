package com.piusvelte.nwsweather.domain.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.piusvelte.nwsweather.data.dto.ResourceDto

sealed class Hideable<out T : Any> {
    data class Show<out T : Any>(val data: T) : Hideable<T>()
    object Hide : Hideable<Nothing>()
}

internal fun <T : Any> T?.hideable(): Hideable<T> {
    return this?.let { Hideable.Show(it) } ?: Hideable.Hide
}

internal inline fun <reified T : Any, R : Any> LiveData<ResourceDto<T>>.mapHideable(crossinline property: (T) -> R): LiveData<Hideable<R>> {
    return map { resource ->
        ((resource as? ResourceDto.Success<T>)?.data?.let { property(it) }).hideable()
    }
}
