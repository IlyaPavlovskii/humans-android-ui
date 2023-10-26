@file:Suppress("UnstableApiUsage")

import java.util.Properties


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {

    val githubProperties = Properties()
    val propsFile = file("github.properties")
    if (propsFile.isFile) {
        propsFile.inputStream().use { githubProperties.load(it) }
    } else {
        githubProperties["github_username"] = "${System.getenv()["GITHUB_USERNAME"]}"
        githubProperties["github_password"] = "${System.getenv()["GITHUB_PASSWORD"]}"
    }

    repositories {
        google()
        mavenCentral()
        mavenLocal()

        maven {
            url = uri("https://github.com/IlyaPavlovskii/humans-android-ui")
            credentials {
                username = githubProperties["github_username"].toString()
                password = githubProperties["github_password"].toString()
            }
        }
    }
}

rootProject.name = "humans-android-ui"

includeBuild("build-logic")

include(":wrappers")
include(":wrappers-compose-ktx")
include(":sample")
