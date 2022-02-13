buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.0.4")
    }
}

group = "fun.stok.executor"
version = "1.0.0"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    buildDir = run {
        val globalBuildDirectory = project.rootProject.projectDir.resolve("build")
        when (project) {
            rootProject -> globalBuildDirectory.resolve(project.name)
            else -> {
                val relativePath = project.projectDir.relativeTo(project.rootProject.projectDir)
                globalBuildDirectory.resolve(relativePath)
            }
        }
    }
}
