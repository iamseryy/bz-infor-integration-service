plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.git.properties)
}

group = "ru.bz"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // SPRING BOOT STARTERS
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.data.jdbc)
    implementation(libs.spring.boot.starter.actuator)

    // KOTLIN & JACKSON
    implementation(libs.jackson.module.kotlin)
    implementation(libs.jackson.datatype.jsr310)

    // DATABASE
    implementation(libs.mssql.jdbc)

    // JWT
    implementation(libs.bundles.jjwt)

    // LOG
    implementation(libs.kotlin.logging)
    implementation(libs.logstash.encoder)

    // TEST
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.spring.rabbit.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    jvmToolchain(24)

    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    failOnNoDiscoveredTests.set(false)
}

springBoot {
    buildInfo()
}

gitProperties {
    keys = listOf("git.branch", "git.commit.id.abbrev", "git.commit.time")
    // ISO-8601
    dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX"
    dateFormatTimeZone = "UTC"
}