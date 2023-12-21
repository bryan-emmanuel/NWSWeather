package com.piusvelte.nwsweather.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CoordinateWithChildren(
    @Embedded val containerEntity: CoordinateContainerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "coordinate_id",
    ) val coordinates: List<CoordinateEntity>,
)