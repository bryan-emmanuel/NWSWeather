package com.piusvelte.data.local.dao

import androidx.room.*
import com.piusvelte.data.local.entity.Point
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {
    @Query("SELECT * FROM point")
    fun getAll(): Flow<List<Point>>

    @Query("SELECT * FROM point WHERE id == :id")
    fun get(id: String): Flow<Point>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(point: Point)

    @Update
    suspend fun update(point: Point)

    @Delete
    suspend fun delete(point: Point)
}
