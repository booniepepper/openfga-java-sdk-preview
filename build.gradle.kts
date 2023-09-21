plugins {
    `java-library`
    groovy
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

val jacksonVersion = "2.14.1"

dependencies {
    implementation("dev.openfga:openfga-sdk:0.0.+")

    // General
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("org.openapitools:jackson-databind-nullable:0.2.1")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    // Groovy
    implementation("org.codehaus.groovy:groovy-all:3.0.15")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain(11)
}