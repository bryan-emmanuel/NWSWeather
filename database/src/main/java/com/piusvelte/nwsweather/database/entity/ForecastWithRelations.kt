package com.piusvelte.nwsweather.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ForecastWithRelations(
    @Embedded val forecast: ForecastEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "forecast_id",
        entity = CoordinateContainerEntity::class,
    ) val coordinates: List<CoordinateWithChildren>,
    @Relation(
        parentColumn = "id",
        entityColumn = "forecast_id",
    ) val periods: List<ForecastPeriodEntity>,
)