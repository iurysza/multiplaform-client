buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.android.tools.build:gradle:4.0.2")
    }
}

group = "com.github.iurysza"
version = "1.0"

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}