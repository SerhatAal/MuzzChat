plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.srhtdev.muzzchat"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.srhtdev.muzzchat"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {

    implementation(appLibraries)
    testImplementation(testLibraries)
    androidTestImplementation(androidTestLibraries)
    kapt(kaptLibraries)
    debugImplementation(debugLibraries)
}

kapt {
    generateStubs = true
    correctErrorTypes = true
}