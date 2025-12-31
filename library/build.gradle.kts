import com.gradleup.librarian.gradle.Librarian

plugins {
  alias(libs.plugins.kotlin.multiplatform)
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
        implementation(libs.kotlinx.datetime)
      }
    }
  }
}

Librarian.module(project)
