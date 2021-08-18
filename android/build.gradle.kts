plugins {
    id("org.jetbrains.compose") version "0.4.0"
    id("com.android.application")
    kotlin("android")
}

group = "com.github.iurysza"
version = "1.0"

repositories {
    google()
    mavenLocal()
}

val composeVersion: String = "1.0.0"
dependencies {
    implementation(project(":common"))

    implementation("com.github.iurysza:vaccination-tracker:1.0.18")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.github.iurysza.android"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}