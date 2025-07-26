plugins {
  kotlin("multiplatform").apply(false)
}

allprojects {
  group = "org.jraf"
  version = "1.1.0"
}

// `./gradlew refreshVersions` to update dependencies
// `./gradlew publishToMavenLocal` to publish to local Maven repository
