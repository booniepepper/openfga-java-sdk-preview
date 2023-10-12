plugins {
    `java-library`
    groovy
    scala
    kotlin("jvm") version "1.9.+"
    id("dev.clojurephant.clojure") version "0.8.+"
    id("com.diffplug.spotless") version "6.+"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.clojars.org/")
    }
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

    // Clojure
    implementation("org.clojure:clojure:1.11.+")
    implementation("org.clojure:tools.namespace:1.3.+") // REPL support
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
}

clojure {
    builds {
        val mybuild by creating {
            sourceRoots.from("src/main/clojure")
        }
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
