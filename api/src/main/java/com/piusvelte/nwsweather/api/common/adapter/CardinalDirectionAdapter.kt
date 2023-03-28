package com.piusvelte.nwsweather.api.common.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.piusvelte.nwsweather.api.common.model.NwsCardinalDirection
import java.lang.reflect.Type

internal class CardinalDirectionAdapter : JsonDeserializer<NwsCardinalDirection> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): NwsCardinalDirection {
        return NwsCardinalDirection.valueOf(json.asString)
    }
}
