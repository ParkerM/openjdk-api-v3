pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

rootProject.name = "adoptopenjdk-api-v3"

include("frontend", "models", "persistence", "updater")
rootProject.children.forEach {
    it.name = "adoptopenjdk-api-v3-${it.name}"
}
