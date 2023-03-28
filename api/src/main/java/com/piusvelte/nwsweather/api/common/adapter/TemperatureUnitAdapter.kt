package com.piusvelte.nwsweather.api.common.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.piusvelte.nwsweather.api.common.model.NwsTemperatureUnit
import com.piusvelte.nwsweather.api.common.model.toTemperatureUnit
import java.lang.reflect.Type

internal class TemperatureUnitAdapter : JsonDeserializer<NwsTemperatureUnit> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): NwsTemperatureUnit {
        return json.asString.toTemperatureUnit()
    }
}
