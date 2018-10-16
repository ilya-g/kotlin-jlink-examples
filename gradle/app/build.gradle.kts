import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
}

val moduleName by extra("org.test.modularApp")

val javaHome = System.getProperty("java.home")

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3-SNAPSHOT:modular")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3-SNAPSHOT:modular")
    api("org.jetbrains.kotlin:kotlin-stdlib:1.3-SNAPSHOT:modular")
    // Cannot use project dependency, need to publish it to mavenLocal and use it from there
//    implementation(project(":library"))
    implementation("org.test:modularLib:1.0-SNAPSHOT")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_9
    targetCompatibility = JavaVersion.VERSION_1_9
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    "compileKotlin"(KotlinCompile::class) {
        doFirst {
            // Currently we have to put all the dependencies in classpath
//            println(classpath.toList())
//            kotlinOptions.freeCompilerArgs += "-Xmodule-path=${classpath.asPath}"
//            classpath = files()
        }
    }
    "compileJava"(JavaCompile::class) {
        inputs.property("moduleName", moduleName)
        doFirst {
            options.compilerArgs = listOf(
                "--module-path", classpath.asPath,
                "--patch-module", "$moduleName=${sourceSets["main"].output.asPath}"
            )
            classpath = files()
        }
    }

    val jar by getting(Jar::class)

    val jlink by registering(Exec::class) {
        val outputDir by extra("$buildDir/jlink")
        inputs.files(configurations.runtimeClasspath)
        inputs.files(jar.archivePath)
        outputs.dir(outputDir)
        dependsOn(jar)
        doFirst {
            println(configurations.runtimeClasspath.toList())
            delete(outputDir)
            commandLine("$javaHome/bin/jlink",
                "--module-path",
                listOf("$javaHome/jmods/", configurations.runtimeClasspath.asPath, jar.archivePath).joinToString(File.pathSeparator),
                "--add-modules", moduleName,
                "--output", outputDir
            )
        }
    }
}