plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildTypes {
        getByName("release") {
            buildConfigField("String", "API_URL", "\"https://dog.ceo/api/\"")

        }
        getByName("debug") {
            buildConfigField("String", "API_URL", "\"https://dog.ceo/api/\"")
        }
    }
}

dependencies {
    implementation(Libraries.App.Network.Retrofit2.core)
    implementation(Libraries.App.Network.Retrofit2.converterMoshi)
    implementation(Libraries.App.Network.Moshi.adapters)
    implementation(Libraries.App.Network.Moshi.core)
    implementation(Libraries.App.Network.Moshi.kotlin)
    implementation(Libraries.App.Hilt.core)


    kapt(Libraries.App.Network.Moshi.codegen)
    kapt(Libraries.App.Hilt.compiler)
}