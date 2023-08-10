plugins {
    `java-library`
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
}

val jacksonVersion = "2.14.1"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(files("../java-sdk/build/libs/openfga-sdk-0.1.jar"))

    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("org.openapitools:jackson-databind-nullable:0.2.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain(11)
}