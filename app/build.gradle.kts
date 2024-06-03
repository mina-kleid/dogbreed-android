plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = SdkVersion.compileSdk

    defaultConfig {
        applicationId = "com.mina.dog.breed"
        minSdk = SdkVersion.minSdk
        targetSdk = SdkVersion.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    hilt {
        enableAggregatingTask = true
    }

    signingConfigs {
        create("release") {
            keyAlias = "dog_breed"
            keyPassword = "dog_breed"
            storeFile = file("$projectDir/dog_breed_keystore.jks")
            storePassword = "dog_breed"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    namespace = "com.mina.dog"
}

dependencies {
    implementation(project(":breed-item"))
    implementation(project(":breed-list"))
    implementation(project(":breed"))

    implementation(Libraries.App.AndroidX.appcompat)
    implementation(Libraries.App.AndroidX.material)
    implementation(Libraries.App.AndroidX.viewPager2)
    implementation(Libraries.App.Kotlin.stdlib)
    implementation(Libraries.App.Lifecycle.viewModel)
    implementation(Libraries.App.Hilt.core)
    implementation(Libraries.App.Navigation.fragment)
    implementation(Libraries.App.Navigation.ui)
    implementation(Libraries.App.Navigation.dynamicFeatures)

    kapt(Libraries.App.Hilt.compiler)
}
