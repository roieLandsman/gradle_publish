
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.0"
    `java-library`
    `maven-publish`
}

group = "experiment.ssi.kotlin_test" // please do not change we don't want to mess the repository
version = "1.0-SNAPSHOT" // change to see that you actually publish something new

val artifactory_url: String? by properties
val artifactory_publish_url: String? by properties
val artifactory_username: String? by properties
val artifactory_password: String? by properties

repositories {
    maven {
        url = uri("${artifactory_url}")
        credentials {
            username = artifactory_username
            password = artifactory_password
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }

    }
    repositories {
        maven { url = uri("$artifactory_publish_url")
            credentials {
                username = artifactory_username
                password = artifactory_password
            }
        }
    }
}



