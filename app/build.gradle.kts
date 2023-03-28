plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.piusvelte.nwsweather"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.lifecycleKtx)
    implementation(AndroidX.liveDataKtx)
    implementation(AndroidX.viewModelKtx)
    implementation(Material.material)
    implementation(Material.composeAdapter)
    implementation(Google.playServicesLocation)
    testImplementation(JUnit.junit)
    androidTestImplementation(Test.junit)
    androidTestImplementation(Test.espressoCore)

    implementation(Dagger.hiltAndroid)
    kapt(Dagger.hiltCompiler)

    implementation(platform(OkHttp.bom))
    implementation(OkHttp.okhttp)
    implementation(OkHttp.logging)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterGson)

    implementation(IntelliJ.coroutines)
    implementation(IntelliJ.playServicesKts)

    implementation(Glide.glide)
    kapt(Glide.compiler)

    debugImplementation(Chucker.debug)
    releaseImplementation(Chucker.release)

    implementation(Room.runtime)
    annotationProcessor(Room.compiler)
    kapt(Room.compiler)
    implementation(Room.ktx)

    implementation(AndroidX.composeActivity)
    implementation(AndroidX.composeAnimation)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeTooling)
    implementation(AndroidX.composeViewModel)
    androidTestImplementation(AndroidX.composeTest)

    implementation(project(":api"))
    implementation(project(":data"))
    implementation(project(":data-nws"))
    implementation(project(":data-local"))
    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}
