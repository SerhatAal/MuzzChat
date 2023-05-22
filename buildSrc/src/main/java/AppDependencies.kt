import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial}"
    const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val composeCoil = "io.coil-kt:coil-compose:${Versions.composeCoil}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val junit = "junit:junit:${Versions.junit}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    val appLibraries = arrayListOf<String>().apply {
        add(Dependencies.coreKtx)
        add(Dependencies.lifecycleRuntime)
        add(Dependencies.activityCompose)
        add(Dependencies.composeUi)
        add(Dependencies.composeUiGraphics)
        add(Dependencies.composeUiToolingPreview)
        add(Dependencies.composeMaterial3)
        add(Dependencies.composeFoundation)
        add(Dependencies.composeRuntime)
        add(Dependencies.composeConstraintLayout)
        add(Dependencies.composeViewModel)
        add(Dependencies.composeCoil)
        add(Dependencies.roomRuntime)
        add(Dependencies.roomKtx)
        add(Dependencies.coroutineCore)
        add(Dependencies.coroutineAndroid)
        add(Dependencies.daggerHilt)
    }


    val androidTestLibraries = arrayListOf<String>().apply {
        add(Dependencies.testExtJunit)
        add(Dependencies.espressoCore)
        add(Dependencies.composeUiJunit)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(Dependencies.junit)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(Dependencies.daggerCompiler)
        add(Dependencies.roomCompiler)
    }

    val debugLibraries = arrayListOf<String>().apply {
        add(Dependencies.composeUiManifest)
        add(Dependencies.composeUiTooling)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}



  