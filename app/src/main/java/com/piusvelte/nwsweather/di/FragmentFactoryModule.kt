package com.piusvelte.nwsweather.di

import androidx.fragment.app.Fragment
import com.piusvelte.nwsweather.forecast.ForecastFragment
import com.piusvelte.nwsweather.point.PointFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
internal interface FragmentFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(ForecastFragment::class)
    fun bindsGridPointFragment(fragment: ForecastFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PointFragment::class)
    fun bindsPointFragment(fragment: PointFragment): Fragment
}
