buildscript {
    val kotlinVersion by extra("1.3.20-eap-25")
    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
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
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        mavenLocal()  // is currently required for publishing modularLib to use it in modularApp
        mavenCentral()
    }
}

