package com.piusvelte.nwsweather.ui

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.InputStream

@Excludes(OkHttpLibraryGlideModule::class)
@GlideModule
class NwsGlideModule : AppGlideModule() {

    // TODO move to DI
    private fun provideUserAgentInterceptor(): Interceptor = Interceptor {
        it.proceed(
            it.request()
                .newBuilder()
                .removeHeader("User-Agent")
                .build()
        )
    }

    private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val interceptor = provideUserAgentInterceptor()
        val client = provideOkHttpClient(interceptor)

        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client),
        )
    }
}