package com.piusvelte.nwsweather.database.dao

import androidx.room.*
import com.piusvelte.nwsweather.database.entity.PointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {
    @Query("SELECT * FROM points")
    fun getAll(): Flow<List<PointEntity>>

    @Query("SELECT * FROM points WHERE id == :id")
    fun get(id: String): Flow<PointEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(point: PointEntity)

    @Update
    suspend fun update(point: PointEntity)

    @Delete
    suspend fun delete(point: PointEntity)
}
