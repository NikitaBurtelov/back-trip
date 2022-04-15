plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation(rootProject.extra["telegram"] as String)
    implementation(rootProject.extra["telegramStarter"] as String)
}