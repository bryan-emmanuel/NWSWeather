object AndroidX {
    const val coreKtx = "androidx.core:core-ktx:1.8.0"
    const val appcompat = "androidx.appcompat:appcompat:1.5.0"

    private const val lifecycleVersion = "2.4.0"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.2"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    private const val composeVersion = "1.2.1"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeAnimation = "androidx.compose.animation:animation:$composeVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"

    const val composeActivity = "androidx.activity:activity-compose:1.5.1"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
}

object Google {
    private const val version = "20.0.0"
    const val playServicesLocation = "com.google.android.gms:play-services-location:$version"
}

object Material {
    private const val version = "1.6.1"
    const val material = "com.google.android.material:material:$version"
    const val composeAdapter = "com.google.android.material:compose-theme-adapter-3:$version"
}

object JUnit {
    const val junit = "junit:junit:4.13.2"
}

object Test {
    const val junit = "androidx.test.ext:junit:1.1.3"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
}

object Dagger {
    private const val version = "2.38.1"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
}

object OkHttp {
    const val bom = "com.squareup.okhttp3:okhttp-bom:4.10.0"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val logging = "com.squareup.okhttp3:logging-interceptor"
}

object Retrofit {
    private const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
}

object IntelliJ {
    private const val version = "1.6.4"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val playServicesKts = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
}

object Chucker {
    private const val version = "3.5.2"
    const val debug = "com.github.chuckerteam.chucker:library:$version"
    const val release = "com.github.chuckerteam.chucker:library-no-op:$version"
}

object Room {
    private const val version = "2.4.3"
    const val runtime = "androidx.room:room-runtime:$version"
    const val compiler = "androidx.room:room-compiler:$version"
    const val ktx = "androidx.room:room-ktx:$version"
}

object Glide {
    private const val version = "4.13.2"
    const val glide = "com.github.bumptech.glide:glide:$version"
    const val compiler = "com.github.bumptech.glide:compiler:$version"
}
