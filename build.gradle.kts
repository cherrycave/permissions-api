plugins {
    kotlin("jvm") version "2.3.21"
}

group = "dev.boecker.cherrycave"
version = "0.1.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}