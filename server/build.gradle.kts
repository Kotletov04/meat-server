plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.allopen)
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springframework.boot.starter.web)
    implementation(libs.jackson.kotlin)
    implementation(project(":core:domain"))
    implementation(project(":core:routing"))
    implementation(project(":core:database"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}

allOpen {
    annotation("org.springframework.context.annotation.Bean")
    annotation("org.springframework.context.annotation.Configuration")
}