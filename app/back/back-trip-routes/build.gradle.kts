plugins {
    kotlin("plugin.jpa")
}

dependencies {
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //developmentOnly("org.springframework.boot:spring-boot-devtools")
    //implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    //implementation("org.springframework.boot:spring-boot-starter-jdbc")
    //implementation("org.springframework.boot:spring-boot-starter-validation")
    //implementation("org.springframework.data:spring-data-jpa")
    implementation(rootProject.extra["jsoup"] as String)
    //implementation(rootProject.extra["postgresql"] as String)
    //implementation(rootProject.extra["jdbc"] as String)
    //runtimeOnly(rootProject.extra["postgresql"] as String)
    //runtimeOnly(rootProject.extra["jdbc"] as String)
}