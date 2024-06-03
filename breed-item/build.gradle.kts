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
    implementation(Libraries.App.Glide.glide)

    kapt(Libraries.App.Hilt.compiler)
}
android {
    namespace = "com.mina.dog.breed.list.item"
}
