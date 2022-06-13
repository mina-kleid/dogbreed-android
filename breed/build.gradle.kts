plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

    implementation(project(":common-models"))
    implementation(project(":common-ui"))
    implementation(project(":storage"))

    implementation(Libraries.App.AndroidX.appcompat)
    implementation(Libraries.App.Kotlin.stdlib)
    implementation(Libraries.App.Lifecycle.viewModel)
    implementation(Libraries.App.Lifecycle.runtime)
    implementation(Libraries.App.Navigation.fragment)
    implementation(Libraries.App.Navigation.ui)
    implementation(Libraries.App.Navigation.dynamicFeatures)
    implementation(Libraries.App.Hilt.core)
    implementation(Libraries.App.glide)

    //compose
    implementation(Libraries.App.Compose.activity)
    implementation(Libraries.App.Compose.materialDesign)
    implementation(Libraries.App.Compose.tooling)
    implementation(Libraries.App.Compose.themeAdapter)

    kapt(Libraries.App.Hilt.compiler)
}