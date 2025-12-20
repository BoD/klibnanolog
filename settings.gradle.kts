rootProject.name = "klibnanolog-root"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://central.sonatype.com/repository/maven-snapshots/")
  }
}

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    mavenLocal()
    mavenCentral()
    maven("https://central.sonatype.com/repository/maven-snapshots/")
  }
}

plugins {
  // See https://splitties.github.io/refreshVersions/setup/
  id("de.fayard.refreshVersions") version "0.60.6"
}


include(":library")
project(":library").name = "klibnanolog"
