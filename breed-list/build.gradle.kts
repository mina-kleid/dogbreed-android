plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    namespace = "com.mina.dog.breed.list"
}

dependencies {

    implementation(project(":breed-item"))
    implementation(project(":common-models"))
    implementation(project(":common-ui"))
    implementation(project(":network"))
    implementation(project(":storage"))

    implementation(Libraries.App.AndroidX.appcompat)
    implementation(Libraries.App.AndroidX.recyclerView)
    implementation(Libraries.App.AndroidX.constraintLayout)
    implementation(Libraries.App.Kotlin.stdlib)
    implementation(Libraries.App.Lifecycle.viewModel)
    implementation(Libraries.App.Lifecycle.runtime)
    implementation(Libraries.App.Navigation.fragment)
    implementation(Libraries.App.Hilt.core)

    kapt(Libraries.App.Hilt.compiler)

    testImplementation(Libraries.Test.Mockito.android)
    testImplementation(Libraries.Test.Mockito.kotlin)
    testImplementation(Libraries.Test.Kotlin.coroutines)
    testImplementation(Libraries.Test.junit)
    testImplementation(Libraries.Test.turbine)
}