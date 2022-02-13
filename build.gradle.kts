import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradle.plugins.kotlin)
        classpath(libs.gradle.plugins.android)
        classpath(libs.semver)
    }
}

plugins {
    alias(libs.plugins.compose) apply(false)
    alias(libs.plugins.kotlin.multiplatform) apply(false)

    alias(libs.plugins.spotless) apply(false)
    alias(libs.plugins.detekt) apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    buildDir = run {
        val globalBuildDirectory = rootProject.projectDir.resolve("build")
        when (project) {
            rootProject -> globalBuildDirectory.resolve(project.name)
            else -> {
                val relativePath = projectDir.relativeTo(rootProject.projectDir)
                globalBuildDirectory.resolve(relativePath)
            }
        }
    }
}

subprojects {
    apply<SpotlessPlugin>()
    apply<DetektPlugin>()

    extensions.configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            ktlint(libs.versions.gradle.plugins.ktlint.get())
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }
    }

    afterEvaluate {
        extensions.configure<DetektExtension> {
            toolVersion = libs.versions.gradle.plugins.detekt.get()
            parallel = true
            buildUponDefaultConfig = true
            autoCorrect = true
        }
    }
}
