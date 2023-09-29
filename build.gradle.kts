plugins {
    `java-library`
    groovy
    scala
    kotlin("jvm") version "1.9.+"
    id("com.diffplug.spotless") version "6.+"
}

repositories {
    mavenCentral()
}

val jacksonVersion = "2.14.+"

dependencies {
    implementation("dev.openfga:openfga-sdk:0.1.+")

    // General
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("org.openapitools:jackson-databind-nullable:0.2.+")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    // Groovy
    implementation("org.codehaus.groovy:groovy-all:3.0.+")

    // Scala
    implementation("org.scala-lang:scala3-library_3:3.0.+")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain(11)
}

sourceSets {
    main {
        scala.srcDirs(listOf("src/main/scala"))
    }
    test {
        scala.srcDirs(listOf("test/main/scala"))
    }
}

spotless {
    groovy {
        greclipse()
        importOrder()
    }
    java {
        palantirJavaFormat()
        removeUnusedImports()
        importOrder()
    }
    kotlin {
        ktlint()
    }
    scala {
        scalafmt()
    }
}

tasks.register("fmt") {
    dependsOn("spotlessApply")
}
