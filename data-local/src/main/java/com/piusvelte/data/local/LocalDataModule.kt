package com.piusvelte.data.local

import android.content.Context
import androidx.room.Room
import com.piusvelte.data.local.dao.PointDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalDataModule {
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): NwsDatabase {
        return Room.databaseBuilder(
            context,
            NwsDatabase::class.java,
            "nws-database",
        ).build()
    }

    @Provides
    fun providesPointDao(db: NwsDatabase): PointDao {
        return db.pointDao()
    }
}
