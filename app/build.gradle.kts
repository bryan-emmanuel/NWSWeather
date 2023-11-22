@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.piusvelte.nwsweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.piusvelte.nwsweather"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.composeBom))
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.hiltNavigationCompose)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(libs.androidx.lifecycle.runtimeKtx)
    implementation(libs.google.material)
    implementation(libs.google.playServicesLocation)
    implementation(libs.jetbrains.kotlinx.coroutinesPlayServices)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.espressoCore)

    implementation(libs.dagger.hiltAndroid)
    ksp(libs.dagger.hiltCompiler)

    implementation(libs.bumptech.glide)
    implementation(libs.bumptech.glideCompose)
    implementation(libs.bumptech.glideOkhttp)
    ksp(libs.bumptech.glideKsp)

    androidTestImplementation(platform(libs.androidx.composeBom))
    androidTestImplementation(libs.androidx.compose.uiTestJunit)
    debugImplementation(libs.androidx.compose.uiTooling)
    debugImplementation(libs.androidx.compose.uiTestManifest)

    implementation(project(":domain"))
}
