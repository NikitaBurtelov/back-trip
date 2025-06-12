rootProject.name = "back"
val rootModuleName = "back-trip"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }
}

include(
    "$rootModuleName-app",
    "$rootModuleName-manager",
    "$rootModuleName-routes",
)