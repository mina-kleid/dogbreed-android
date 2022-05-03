plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

dependencies {

    implementation(project(":common-models"))

    implementation(Libraries.App.Kotlin.stdlib)
    implementation(Libraries.App.Hilt.core)

    kapt(Libraries.App.Hilt.compiler)
}