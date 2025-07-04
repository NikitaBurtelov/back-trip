plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation(rootProject.extra["telegramStarter"] as String)
    implementation(rootProject.extra["telegramClient"] as String)
    implementation(rootProject.extra["telegramBots"] as String)
    implementation(rootProject.extra["okHttp"] as String)
    implementation(rootProject.extra["postgresql"] as String)
    implementation(rootProject.extra["oracleJdbc"] as String)
    runtimeOnly(rootProject.extra["postgresql"] as String)
    runtimeOnly(rootProject.extra["oracleJdbc"] as String)
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("org.app.back.trip.BackTripApplicationKt")
}