plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

dependencies {

    implementation(project(":breed-item"))
    implementation(project(":common-models"))
    implementation(project(":common-ui"))
    implementation(project(":network"))

    implementation(Libraries.App.AndroidX.appcompat)
    implementation(Libraries.App.Kotlin.stdlib)
    implementation(Libraries.App.Lifecycle.viewModel)
    implementation(Libraries.App.Lifecycle.runtime)
    implementation(Libraries.App.Navigation.fragment)
    implementation(Libraries.App.Navigation.ui)
    implementation(Libraries.App.Navigation.dynamicFeatures)
    implementation(Libraries.App.Hilt.core)

    kapt(Libraries.App.Hilt.compiler)
}