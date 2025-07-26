plugins {
  kotlin("multiplatform")
  id("maven-publish")
  id("org.jetbrains.dokka")
  id("signing")
}

// Generate Javadoc (Dokka) Jar
val dokkaHtmlJar = tasks.register<Jar>("dokkaHtmlJar") {
  archiveClassifier.set("javadoc")
  from("${layout.buildDirectory}/dokka")
  dependsOn(tasks.dokkaGenerate)
}

kotlin {
  jvm()
  jvmToolchain(11)
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

publishing {
  repositories {
    maven {
      // Note: declare your user name / password in your home's gradle.properties like this:
      // mavenCentralNexusUsername = <user name>
      // mavenCentralNexusPassword = <password>
      url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
      name = "mavenCentralNexus"
      credentials(PasswordCredentials::class)
    }
  }

  publications.withType<MavenPublication>().forEach { publication ->
    publication.artifact(dokkaHtmlJar)

    publication.pom {
      name.set("klibnanolog")
      description.set("The smallest logging library for Kotlin that you don't need. (But I do).")
      url.set("https://github.com/BoD/klibnanolog")
      licenses {
        license {
          name.set("The Apache License, Version 2.0")
          url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          distribution.set("repo")
        }
      }
      developers {
        developer {
          id.set("BoD")
          name.set("Benoit 'BoD' Lubek")
          email.set("BoD@JRAF.org")
          url.set("https://JRAF.org")
          organization.set("JRAF.org")
          organizationUrl.set("https://JRAF.org")
          roles.set(listOf("developer"))
          timezone.set("+1")
        }
      }
      scm {
        connection.set("scm:git:https://github.com/BoD/klibnanolog")
        developerConnection.set("scm:git:https://github.com/BoD/klibnanolog")
        url.set("https://github.com/BoD/klibnanolog")
      }
      issueManagement {
        url.set("https://github.com/BoD/klibnanolog/issues")
        system.set("GitHub Issues")
      }
    }
  }
}

signing {
  // Note: declare the signature key, password and file in your home's gradle.properties like this:
  // signing.keyId=<8 character key>
  // signing.password=<your password>
  // signing.secretKeyRingFile=<absolute path to the gpg private key>
  sign(publishing.publications)
}

// Workaround for https://youtrack.jetbrains.com/issue/KT-46466
val dependsOnTasks = mutableListOf<String>()
tasks.withType<AbstractPublishToMaven>().configureEach {
  dependsOnTasks.add(this.name.replace("publish", "sign").replaceAfter("Publication", ""))
  dependsOn(dependsOnTasks)
}

dokka {
  dokkaPublications.html {
    outputDirectory.set(rootProject.file("docs"))
  }
}

// Run `./gradlew dokkaHtml` to generate the docs
// Run `./gradlew publishToMavenLocal` to publish to the local maven repo
// Run `./gradlew publish` to publish to Maven Central (then go to https://oss.sonatype.org/#stagingRepositories and "close", and "release")
