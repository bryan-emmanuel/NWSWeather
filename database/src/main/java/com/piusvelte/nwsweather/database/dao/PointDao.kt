package com.piusvelte.nwsweather.database.dao

import androidx.room.*
import com.piusvelte.nwsweather.database.entity.PointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {

    @Query(
"""
SELECT * FROM points WHERE
 geometry_coordinate_longitude >= :minLongitude AND
 geometry_coordinate_longitude <= :maxLongitude AND
 geometry_coordinate_latitude >= :minLatitude AND
 geometry_coordinate_latitude <= :maxLatitude
"""
    )
    fun search(
        minLongitude: Float,
        maxLongitude: Float,
        minLatitude: Float,
        maxLatitude: Float,
    ): Flow<PointEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(point: PointEntity)
}
