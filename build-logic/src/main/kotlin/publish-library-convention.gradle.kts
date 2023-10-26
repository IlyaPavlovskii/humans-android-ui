@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.SonatypeHost
import java.util.Properties

plugins {
    id("com.vanniktech.maven.publish")
}

val versionSuffix = when (System.getenv("SNAPSHOT")) {
    null -> "-TEST"
    "true" -> "-SNAPSHOT"
    else -> ""
}
project.version = project.version.toString() + versionSuffix

fun readGithubProperties(): Properties {
    val githubProperties = Properties()
    project.rootProject.file("github.properties")
        .takeIf { file -> file.exists() && file.isFile }
        ?.also { file ->
            file.inputStream().use { fis -> githubProperties.load(fis) }
        } ?: run {
        githubProperties["github_username"] = System.getenv("GITHUB_USERNAME") ?: ""
        githubProperties["github_password"] = System.getenv("GITHUB_PASSWORD") ?: ""
    }
    return githubProperties
}

publishing {
    repositories {
        maven {
            url = uri("https://github.com/IlyaPavlovskii/humans-android-ui")
            val githubProperties: Properties = readGithubProperties()
            credentials {
                username = githubProperties.getProperty("github_username")
                password = githubProperties.getProperty("github_password")
            }
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = project.group.toString(),
        artifactId = project.name,
        version = project.version.toString()
    )

    pom {
        name.set("humans-android-ui")
        description.set("Libraries that help to simplify building UI.")
        inceptionYear.set("2023")
        url.set("https://github.com/IlyaPavlovskii/humans-android-ui")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/IlyaPavlovskii/humans-android-ui/blob/main/LICENSE.md")
                distribution.set("https://github.com/IlyaPavlovskii/humans-android-ui/blob/main/LICENSE.md")
            }
        }
        developers {
            developer {
                id.set("Ilia Pavlovskii")
                name.set("Ilia Pavlovskii")
                url.set("trane91666@gmail.com")
            }
        }
        scm {
            url.set("https://github.com/IlyaPavlovskii/humans-android-ui")
            connection.set("scm:git:github.com/IlyaPavlovskii/humans-android-ui.git")
            developerConnection.set("scm:git:ssh://github.com/IlyaPavlovskii/humans-android-ui.git")
        }
    }
}
