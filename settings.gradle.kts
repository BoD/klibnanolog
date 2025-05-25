plugins {
  // See https://splitties.github.io/refreshVersions/setup/
  id("de.fayard.refreshVersions") version "0.60.5"
}

rootProject.name = "klibnanolog-root"

include(":library")
project(":library").name = "klibnanolog"
