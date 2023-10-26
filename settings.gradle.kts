@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "humans-android-ui"

includeBuild("build-logic")

include(":wrappers")
include(":wrappers-compose-ktx")
include(":sample")
