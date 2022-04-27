plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    val gradleVersion = "7.1.3"
    val kotlinVersion = "1.6.10"
    implementation("com.android.tools.build:gradle:$gradleVersion")
    implementation("com.android.tools.build:gradle-api:$gradleVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

}