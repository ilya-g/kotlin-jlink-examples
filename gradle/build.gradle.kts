buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3-SNAPSHOT")
    }
}

allprojects {
    group = "org.test"
    version = "1.0-SNAPSHOT"


    repositories {
        mavenLocal()
        mavenCentral()
    }
}

