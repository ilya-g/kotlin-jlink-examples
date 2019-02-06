buildscript {
    val kotlinVersion by extra("1.3.21")
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    group = "org.test"
    version = "1.0-SNAPSHOT"


    repositories {
        mavenLocal()  // is currently required for publishing modularLib to use it in modularApp
        mavenCentral()
    }
}

