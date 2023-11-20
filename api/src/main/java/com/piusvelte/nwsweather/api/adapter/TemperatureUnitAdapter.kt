package com.piusvelte.nwsweather.api.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.piusvelte.nwsweather.api.model.NwsTemperatureUnit
import com.piusvelte.nwsweather.api.model.toTemperatureUnit
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
