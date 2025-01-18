plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(rootProject.extra["telegramStarter"] as String)
    implementation(rootProject.extra["telegramBots"] as String)
    implementation(rootProject.extra["postgresql"] as String)
    implementation(rootProject.extra["jdbc"] as String)
    runtimeOnly(rootProject.extra["postgresql"] as String)
    runtimeOnly(rootProject.extra["jdbc"] as String)
}