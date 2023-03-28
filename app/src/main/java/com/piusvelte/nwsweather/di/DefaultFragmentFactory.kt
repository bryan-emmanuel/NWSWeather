package com.piusvelte.nwsweather.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
internal class DefaultFragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val provider = providers[fragmentClass]
            ?: providers.entries.firstOrNull { fragmentClass.isAssignableFrom(it.key) }?.value
        return provider?.get() ?: super.instantiate(classLoader, className)
    }
}
