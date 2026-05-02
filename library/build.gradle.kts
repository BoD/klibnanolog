@file:OptIn(ExperimentalWasmDsl::class)

import com.gradleup.librarian.gradle.Librarian
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
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
  wasmJs {
    browser()
    compilerOptions {
      target.set("es2015")
    }
  }
  android {
    namespace = "org.jraf.klibnanolog"
    compileSdk { version = release(36) }
  }

  applyDefaultHierarchyTemplate()
  sourceSets {
    val notAndroidMain by creating {
      dependsOn(commonMain.get())
    }
    jvmMain.get().dependsOn(notAndroidMain)
    nativeMain.get().dependsOn(notAndroidMain)
    webMain.get().dependsOn(notAndroidMain)

    commonMain {
      dependencies {
        implementation(libs.kotlinx.datetime)
      }
    }

    jvmMain {
      dependencies {
        implementation(libs.slf4j.api)
      }
    }
  }
}

Librarian.module(project)
