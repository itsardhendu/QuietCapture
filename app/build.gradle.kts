plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.asr.quietcapture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.asr.quietcapture"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    dependencies {
        implementation (libs.androidx.navigation.compose)
    }
    // CameraX core library for camera functionality
    implementation(libs.camera.core)

    // CameraX Camera2 implementation
    implementation(libs.androidx.camera.camera2)

    // CameraX lifecycle library for integrating camera with lifecycle-aware components
    implementation(libs.androidx.camera.lifecycle)

    // CameraX video library for video recording functionalities
    implementation(libs.androidx.camera.video)

    // CameraX view library for camera UI components
    implementation(libs.androidx.camera.view)

    // CameraX extensions for additional features like HDR, Night mode
    implementation(libs.androidx.camera.extensions)

    // Core Kotlin extensions for Android
    implementation(libs.androidx.core.ktx)

    // Lifecycle runtime library for lifecycle-aware components with Kotlin extensions
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose library for using Compose in activities
    implementation(libs.androidx.activity.compose)

    // Compose BOM (Bill of Materials) to manage Compose dependencies versions
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI library for building UI with Compose
    implementation(libs.androidx.ui)

    // Compose graphics library for graphics operations in Compose
    implementation(libs.androidx.ui.graphics)

    // Compose tooling library for UI previews
    implementation(libs.androidx.ui.tooling.preview)

    // Material Design components for Compose
    implementation(libs.androidx.material3)

    // Compose Foundation library for foundational Compose components
    implementation(libs.androidx.foundation)

    // Accompanist library for handling permissions in Compose
    implementation(libs.accompanist.permissions)

    // Firebase Crashlytics build tools for crash reporting
    implementation(libs.firebase.crashlytics.buildtools)

    // JUnit library for unit testing
    testImplementation(libs.junit)

    // AndroidX JUnit extensions for testing
    androidTestImplementation(libs.androidx.junit)

    // AndroidX Espresso core library for UI testing
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose BOM for managing Compose dependencies versions in tests
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Compose UI test library for UI tests with JUnit4
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Compose tooling library for debugging
    debugImplementation(libs.androidx.ui.tooling)

    // Compose test manifest library for testing
    debugImplementation(libs.androidx.ui.test.manifest)
}