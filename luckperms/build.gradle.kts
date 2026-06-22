plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.4.0"
    `maven-publish`
}

group = "dev.boecker.cherrycave.permissions"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.ktor:ktor-bom:3.5.0"))
    implementation("io.ktor:ktor-client-core")
    implementation("io.ktor:ktor-client-cio")
    implementation("io.ktor:ktor-client-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
}

kotlin {
    jvmToolchain(25)
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
                    description.set("luckperms api wrapper")
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