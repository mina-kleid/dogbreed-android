plugins {
    id("base-android-library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}
android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
    namespace = "com.mina.dog.breed.storage"
}

dependencies {

    implementation(Libraries.App.Room.runtime)
    implementation(Libraries.App.Room.ktx)
    implementation(Libraries.App.Hilt.core)
    implementation(Libraries.App.Kotlin.serialization)

    kapt(Libraries.App.Room.annotationProcessor)
    kapt(Libraries.App.Hilt.compiler)
}