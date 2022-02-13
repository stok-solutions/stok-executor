import net.swiftzer.semver.SemVer

plugins {
    alias(libs.plugins.compose)
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(projects.bundles.ui.common)
    implementation(libs.androidx.activityCompose)
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "${project.group}.android"
        minSdk = 24
        targetSdk = 31
        versionCode = SemVer.parse(project.version as String).run { (100 * major) + (10 * minor) + (1 * patch) }
        versionName = project.version as String
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
