enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "stok-executor"

// Include subprojects
fileTree(rootProject.projectDir) {
    include("**/build.gradle.kts")
    exclude("build.gradle.kts") // Exclude root build.gradle.kts
    exclude("**/buildSrc") // Exclude build sources
    exclude(".*") // Exclude hidden sources
}
    .asSequence()
    .map { relativePath(it.parent).replace(File.separator, ":") }
    .forEach { include(it) }
