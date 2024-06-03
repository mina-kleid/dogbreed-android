plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

dependencies {

    implementation(Libraries.App.Glide.glide)
    implementation(Libraries.App.Hilt.core)

    kapt(Libraries.App.Hilt.compiler)
}
android {
    namespace = "com.mina.dog.breed.common.ui"
}
