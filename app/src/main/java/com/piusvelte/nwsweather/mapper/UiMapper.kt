package com.piusvelte.nwsweather.mapper

import android.location.Location
import com.piusvelte.nwsweather.domain.model.GeoLocation

fun Location.mapDomain() = GeoLocation(
    latitude = latitude.toFloat(),
    longitude = longitude.toFloat(),
)

fun GeoLocation.mapState() = floatArrayOf(latitude, longitude)

fun FloatArray.mapLocation() = GeoLocation(first(), last())