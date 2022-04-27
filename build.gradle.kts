// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libraries.App.Hilt.gradlePlugin)
        classpath(Libraries.App.Navigation.sageArgsPlugin)
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
