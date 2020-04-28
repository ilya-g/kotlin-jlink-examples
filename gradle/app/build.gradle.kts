import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
}

val moduleName by extra("org.test.modularApp")

val javaHome = System.getProperty("java.home")

dependencies {
    implementation(project(":library"))
    testImplementation("junit", "junit", "4.12")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_9
    targetCompatibility = JavaVersion.VERSION_1_9
    modularity.inferModulePath.set(true)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "9"
}

tasks {
    compileJava {
        inputs.property("moduleName", moduleName)
        options.javaModuleMainClass.set("org.test.modularApp.HelloKt")
        options.compilerArgs = listOf(
            "--patch-module", "$moduleName=${sourceSets.main.get().output.asPath}"
        )
    }

    val jlink by registering(Exec::class) {
        val outputDir by extra("$buildDir/jlink")
        inputs.files(configurations.runtimeClasspath)
        inputs.files(jar)
        outputs.dir(outputDir)
        doFirst {
            val modulePath = files(jar) + configurations.runtimeClasspath.get()
            logger.lifecycle(modulePath.joinToString("\n", "jlink module path:\n"))
            delete(outputDir)
            commandLine("$javaHome/bin/jlink",
                "--module-path",
                listOf("$javaHome/jmods/", modulePath.asPath).joinToString(File.pathSeparator),
                "--add-modules", moduleName,
                "--output", outputDir,
                "--launcher", "modularApp=org.test.modularApp"
            )
        }
    }
}
