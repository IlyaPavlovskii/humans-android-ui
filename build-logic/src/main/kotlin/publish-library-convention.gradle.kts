@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
}

val versionSuffix = when (System.getenv("SNAPSHOT")) {
    null -> "-TEST"
    "true" -> "-SNAPSHOT"
    else -> ""
}
project.version = project.version.toString() + versionSuffix

mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01)

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

//    signAllPublications()
}
