import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.spring") version "2.1.21"
    kotlin("plugin.allopen") version "2.1.21"
    kotlin("plugin.noarg") version "2.1.21"
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("jacoco")
}

group = "org.app.back.trip"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

extra.apply {
    set("kotlinCoroutines", "1.8.1")
    set("postgresql", "42.7.3")
    set("oracleJdbc", "23.4.0.0")
    set("telegramBots", "6.9.7.1")
    set("dotenv", "6.4.1")
    set("jsoup", "1.17.2")
    set("springKafka", "3.2.2")
    set("springRetry", "2.0.3")
    set("springDoc", "2.5.1")
    set("kotlinLogging", "3.0.5")
    set("junit", "5.11.0")
    set("mockito", "5.12.0")
    set("mockitoKotlin", "5.3.1")
}

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone")
    maven("https://repo.spring.io/snapshot")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.extra["kotlinCoroutines"]}")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-logging")

    implementation("org.postgresql:postgresql:${rootProject.extra["postgresql"]}")
    implementation("com.oracle.database.jdbc:ojdbc11:${rootProject.extra["oracleJdbc"]}")
    implementation("org.telegram:telegrambots:${rootProject.extra["telegramBots"]}")
    implementation("org.telegram:telegrambots-spring-boot-starter:${rootProject.extra["telegramBots"]}")
    implementation("io.github.cdimascio:dotenv-kotlin:${rootProject.extra["dotenv"]}")
    implementation("org.jsoup:jsoup:${rootProject.extra["jsoup"]}")

    implementation("org.springframework.kafka:spring-kafka:${rootProject.extra["springKafka"]}")
    implementation("org.springframework.retry:spring-retry:${rootProject.extra["springRetry"]}")
    implementation("org.springdoc:springdoc-openapi-ui:${rootProject.extra["springDoc"]}")

    implementation("io.github.microutils:kotlin-logging-jvm:${rootProject.extra["kotlinLogging"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.extra["junit"]}")
    testImplementation("org.mockito:mockito-core:${rootProject.extra["mockito"]}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${rootProject.extra["mockitoKotlin"]}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "21"
}

tasks.test {
    useJUnitPlatform()
}
