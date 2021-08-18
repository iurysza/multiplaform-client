import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.4.0"
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "com.github.iurysza"
version = "1.0"

repositories {
    google()
    mavenLocal()
}

val composeVersion: String = "1.0.0"
kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.iurysza:vaccination-tracker:1.0.18")
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.github.iurysza:vaccination-tracker:1.0.18")
                api("androidx.core:core-ktx:1.6.0")
                api("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
                api("androidx.activity:activity-compose:${composeVersion}")
                api("androidx.compose.ui:ui:${composeVersion}")
                api("androidx.compose.material:material:${composeVersion}")
                api("androidx.compose.ui:ui-tooling:${composeVersion}")
                api("androidx.activity:activity-compose:1.3.0")
                api("com.google.android.material:material:1.4.0")
                api("com.google.accompanist:accompanist-systemuicontroller:0.15.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("com.github.iurysza:vaccination-tracker-jvm:1.0.18")
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}