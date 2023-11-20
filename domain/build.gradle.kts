@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.piusvelte.domain"
    compileSdk = 34

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
    implementation(libs.dagger.hiltAndroid)
    ksp(libs.dagger.hiltCompiler)

    implementation(platform(libs.androidx.composeBom))
    implementation(libs.androidx.compose.runtime)

    implementation(libs.androidx.lifecycle.livedataKtx)

    implementation(project(":data"))
    implementation(project(":data-nws"))
}
