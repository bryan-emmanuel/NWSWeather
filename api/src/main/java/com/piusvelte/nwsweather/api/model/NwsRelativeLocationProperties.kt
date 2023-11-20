package com.piusvelte.nwsweather.api.model

import com.google.gson.annotations.SerializedName
import com.piusvelte.nwsweather.api.model.NwsUnitValue

data class NwsRelativeLocationProperties(
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("distance") val distance: NwsUnitValue,
    @SerializedName("bearing") val bearing: NwsUnitValue,
)
