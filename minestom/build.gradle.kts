plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "dev.boecker.cherrycave.permissions"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":luckperms"))
    compileOnly("net.minestom:minestom:2026.06.20-26.1.2")
    implementation("net.kyori:adventure-text-minimessage:5.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

kotlin {

}

publishing {
    repositories {
        maven {
            setUrl("https://maven.boecker.dev/releases")

            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_SECRET")
            }
        }
    }

    publications {
        publications {
            create<MavenPublication>(project.name) {
                from(components["kotlin"])
                pom {
                    name.set(project.name)
                    description.set("minestom library for integrating luckperms")
                    url.set("https://github.com/cherrycave/permissions-api")

                    licenses {
                        license {
                            name.set("AGPL-3.0 License")
                            url.set("https://github.com/cherrycave/permissions-api/LICENSE")
                        }
                    }

                    developers {
                        developer {
                            name.set("Lou Emma Böcker")
                            email.set("lou@boecker.dev")
                            organizationUrl.set("https://www.boecker.dev")
                        }
                    }
                }
            }
        }
    }
}

kotlin {
    jvmToolchain(25)
}