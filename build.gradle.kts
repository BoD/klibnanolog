allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
  }

  group = "org.jraf"
  version = "1.1.0"
}

plugins {
  kotlin("multiplatform").apply(false)
}

// `./gradlew refreshVersions` to update dependencies
// `./gradlew publishToMavenLocal` to publish to local Maven repository
