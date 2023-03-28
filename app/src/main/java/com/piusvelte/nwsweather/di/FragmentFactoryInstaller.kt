package com.piusvelte.nwsweather.di

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
internal class FragmentFactoryInstaller @Inject constructor(
    activity: AppCompatActivity,
    factory: DefaultFragmentFactory,
) {
    init {
        activity.supportFragmentManager.fragmentFactory = factory
    }
}
