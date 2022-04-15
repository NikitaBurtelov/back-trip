plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation(rootProject.extra["telegram"] as String)
}