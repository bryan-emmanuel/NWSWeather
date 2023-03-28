package com.piusvelte.nwsweather.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal object ActivityModule {
    @Provides
    @ActivityScoped
    fun provides(activity: Activity): AppCompatActivity {
        return activity as AppCompatActivity
    }
}
