package com.piusvelte.nwsweather.api.common.adapter

import com.google.gson.*
import com.piusvelte.nwsweather.api.common.model.NwsCoordinate
import java.lang.reflect.Type

internal class CoordinateAdapter : JsonDeserializer<NwsCoordinate> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): NwsCoordinate {
        val points =
            json as? JsonArray ?: throw JsonParseException("JsonArray expected, found $json")
        if (points.size() != 2) throw JsonParseException("JsonArray size of 2 expected, found ${points.size()}")
        val latitude = points.get(0).asFloat
        val longitude = points.get(1).asFloat
        return NwsCoordinate(latitude, longitude)
    }
}
