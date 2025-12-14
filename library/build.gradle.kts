import com.gradleup.librarian.gradle.Librarian

plugins {
  kotlin("multiplatform")
}

kotlin {
  jvm()
  macosArm64()
  linuxX64()
  linuxArm64()
  js {
    browser()
    compilerOptions {
      target.set("es2015")
    }
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(KotlinX.datetime)
      }
    }
  }
}

Librarian.module(project)

// Run `./gradlew publishToMavenLocal` to publish to the local maven repo
