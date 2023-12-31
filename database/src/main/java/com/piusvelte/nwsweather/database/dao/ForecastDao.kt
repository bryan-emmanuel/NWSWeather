package com.piusvelte.nwsweather.database.dao

import androidx.room.*
import com.piusvelte.nwsweather.database.entity.CoordinateContainerEntity
import com.piusvelte.nwsweather.database.entity.CoordinateEntity
import com.piusvelte.nwsweather.database.entity.ForecastEntity
import com.piusvelte.nwsweather.database.entity.ForecastPeriodEntity
import com.piusvelte.nwsweather.database.entity.ForecastWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ForecastDao {

    @Transaction
    @Query("SELECT * FROM forecasts WHERE office == :office AND grid_x == :gridX AND grid_y == :gridY LIMIT 1")
    abstract fun get(office: String, gridX: Int, gridY: Int): Flow<ForecastWithRelations?>

    suspend fun insert(forecast: ForecastWithRelations) {
        val forecastId = insert(forecast.forecast)

        forecast.coordinates.forEach { container ->
            val coordinateId = insert(container.containerEntity.copy(forecastId = forecastId))

            container.coordinates.forEach {
                insert(it.copy(coordinateId = coordinateId))
            }
        }

        forecast.periods.forEach { period ->
            insert(period.copy(forecastId = forecastId))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(forecast: ForecastEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(period: ForecastPeriodEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(coordinate: CoordinateContainerEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(coordinate: CoordinateEntity): Long
}
