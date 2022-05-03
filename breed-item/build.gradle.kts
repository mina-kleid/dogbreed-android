plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

dependencies {

    implementation(project(":common-models"))
    implementation(project(":common-ui"))

    implementation(Libraries.App.AndroidX.recyclerView)
    implementation(Libraries.App.AndroidX.constraintLayout)
    implementation(Libraries.App.Kotlin.stdlib)
    implementation(Libraries.App.Hilt.core)

    kapt(Libraries.App.Hilt.compiler)
}