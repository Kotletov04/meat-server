plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.kotlin.allopen)
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}


dependencies {
    implementation(libs.hibernate.orm)
    implementation(libs.postgresql.driver)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.liquibase.core)
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    runtimeOnly(libs.postgresql)
    testImplementation(kotlin("test"))
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}