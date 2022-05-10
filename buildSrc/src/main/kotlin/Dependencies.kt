object Versions {
    const val navVersion = "2.4.2"
    const val roomVersion = "2.4.1"
    const val moshiVersion = "1.13.0"
    const val retrofitVersion = "2.9.0"
    const val coroutineVersion = "1.4.3"
    const val hiltVersion = "2.40"
    const val kotlinVersion = "1.6.10"
}

object SdkVersion {

    const val compileSdk = 31
    const val minSdk = 26
    const val targetSdk = 31
}

object Libraries {
    object App {

        object AndroidX {
            const val annotations = "androidx.annotation:annotation:1.2.0"
            const val appcompat = "androidx.appcompat:appcompat:1.3.1"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
            const val core = "androidx.core:core-ktx:1.6.0"
            const val viewBinding = "androidx.databinding:viewbinding:4.2.0@aar"
            const val fragment = "androidx.fragment:fragment-ktx:1.6.0"
            const val material = "com.google.android.material:material:1.5.0"
            const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"
            const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"
        }

        object Hilt {
            const val core = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
            const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
            const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
        }

        object Kotlin {
            const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
        }

        object Lifecycle {

            const val common = "androidx.lifecycle:lifecycle-common-java8:2.4.0"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
        }

        object Navigation {
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
            const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
            const val dynamicFeatures =
                "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navVersion}"
            const val sageArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
            const val annotationProcessor = "androidx.room:room-compiler:${Versions.roomVersion}"
            const val ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
        }

        object Network {
            object Retrofit2 {
                const val converterMoshi =
                    "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
                const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
            }

            object Moshi {
                const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshiVersion}"
                const val core = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
                const val codegen =
                    "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
                const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
            }
        }

        const val glide = "com.github.bumptech.glide:glide:4.11.0"
    }

    object Test {
        object Mockito {
            const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
            const val android = "org.mockito:mockito-android:4.4.0"
        }

        object Kotlin {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineVersion}"
        }

        const val junit = "junit:junit:4.13.2"
        const val turbine = "app.cash.turbine:turbine:0.7.0"
    }
}

