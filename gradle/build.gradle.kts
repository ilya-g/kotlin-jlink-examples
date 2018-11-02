buildscript {
    val kotlinVersion by extra("1.3.20-dev-1397")
    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
        mavenLocal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    group = "org.test"
    version = "1.0-SNAPSHOT"


    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
        mavenLocal()  // is currently required for publishing modularLib to use it in modularApp
        mavenCentral()
    }
}

