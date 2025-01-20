buildscript {
    extra.apply {
        set("kotlinVersion", "1.6.0")
        set("springBootVersion", "2.6.1")
    }

    repositories {
        mavenCentral()
    }

    val kotlinVersion = rootProject.extra["kotlinVersion"]
    val springBootVersion = rootProject.extra["springBootVersion"]
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
    }
}

val kotlinVersion = rootProject.extra["kotlinVersion"]
val springBootVersion = rootProject.extra["springBootVersion"]

extra.apply {
    set("kotlinStdlibJdk11", "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    set("kotlinReflect", "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    set("kotlinxCoroutinesCore", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")

    set("springBootTest", "org.springframework.boot:spring-boot-test:$springBootVersion")
    set("springBootStarterTest", "org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    set("springBootStarterWeb", "org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    set("springBootStarterWebServices", "org.springframework.boot:spring-boot-starter-web-services:$springBootVersion")
    set("springBootStarterDataJpa", "org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    set("springBootStarterActuator", "org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")

    //Tg api
    set("telegramBots", "org.telegram:telegrambots:6.9.7.1")

    //DataBase
    set("postgresql", "org.postgresql:postgresql:42.3.1")
    set("jdbc", "com.oracle.database.jdbc:ojdbc8")

    //Jsoup
    set("jsoup", "org.jsoup:jsoup:1.14.3")

    set("springDataKeyValue", "org.springframework.data:spring-data-keyvalue:2.6.0")
    set("springKafka", "org.springframework.kafka:spring-kafka:2.8.0")
    set("springRetry", "org.springframework.retry:spring-retry:1.3.1")

    set("logging", "io.github.microutils:kotlin-logging-jvm:2.1.21")
    set("springBootStarterLogging", "org.springframework.boot:spring-boot-starter-logging")

    set("junit", "junit:junit:4.12")

    set("dotEnv", "io.github.cdimascio:dotenv-kotlin:6.2.2")

    set("junitJupiterApi", "org.junit.jupiter:junit-jupiter-api:5.8.2")
    set("junitJupiterEngine", "org.junit.jupiter:junit-jupiter-engine:5.8.2")
    set("junitJupiterParams", "org.junit.jupiter:junit-jupiter-params:5.8.2")
    set("junitVintageEngine", "org.junit.vintage:junit-vintage-engine:5.8.2")
    set("junitJupiterLauncher", "org.junit.platform:junit-platform-launcher:1.8.2")
    set("telegramStarter", "org.telegram:telegrambots-spring-boot-starter:5.7.1")

    set("mockitoCore", "org.mockito:mockito-core:4.1.0")
    set("mockitoJupiter", "org.mockito:mockito-junit-jupiter:4.1.0")
    set("mockitoInline", "org.mockito:mockito-inline:4.1.0")
    set("mockitoKotlin", "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    set("springDocOpenApiUi", "org.springdoc:springdoc-openapi-ui:1.6.1")
}

configure(subprojects) {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    apply(plugin = "org.springframework.boot")
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "idea")
    apply(plugin = "jacoco")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring")
    apply(plugin = "java-library")

    group = "org.app.back.trip"
    version = "1.0-SNAPSHOT"


    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(arrayOf("-parameters", "-Xdoclint:none", "-Xlint:all"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        "implementation"(rootProject.extra["kotlinStdlibJdk11"] as String)
        "implementation"(rootProject.extra["kotlinReflect"] as String)
        "implementation"(rootProject.extra["kotlinxCoroutinesCore"] as String)

        "implementation"(rootProject.extra["springBootStarterActuator"] as String)
        "implementation"(rootProject.extra["springBootStarterWeb"] as String)

        "implementation"(rootProject.extra["logging"] as String)
        "implementation"(rootProject.extra["springBootStarterLogging"] as String)

        "implementation"(rootProject.extra["dotEnv"] as String)

        "testImplementation"(rootProject.extra["springBootTest"] as String)
        "testImplementation"(rootProject.extra["springBootStarterTest"] as String)

        "testImplementation"(rootProject.extra["junitJupiterApi"] as String)
        "testImplementation"(rootProject.extra["junitJupiterEngine"] as String)
        "testImplementation"(rootProject.extra["junitJupiterParams"] as String)
        "testImplementation"(rootProject.extra["junitJupiterParams"] as String)
        "testImplementation"(rootProject.extra["junitJupiterLauncher"] as String)
        "testImplementation"(rootProject.extra["mockitoCore"] as String)
        "testImplementation"(rootProject.extra["mockitoJupiter"] as String)
        "testImplementation"(rootProject.extra["mockitoKotlin"] as String)
        "testImplementation"(rootProject.extra["mockitoInline"] as String)
    }

    tasks.withType<Jar> {
        archiveBaseName.set(project.name)
        archiveVersion.set("1.0.0")
    }
}