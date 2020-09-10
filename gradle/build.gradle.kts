buildscript {
    val kotlinVersion by extra("1.4.10")
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
        mavenCentral()
    }
}

