rootProject.name = "klibnanolog-root"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    mavenCentral()
    mavenLocal()
  }
}

plugins {
  // See https://splitties.github.io/refreshVersions/setup/
  id("de.fayard.refreshVersions") version "0.60.5"
}


include(":library")
project(":library").name = "klibnanolog"
