import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
}

val kotlinVersion: String by rootProject.extra

val moduleName by extra("org.test.modularApp")

val javaHome = System.getProperty("java.home")

dependencies {
    implementation(project(":library"))
    testImplementation("junit", "junit", "4.12")
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
        inputs.files(jar.archiveFile)
        outputs.dir(outputDir)
        dependsOn(jar)
        doFirst {
            val runtimeClasspath = configurations.runtimeClasspath.get()
            println(runtimeClasspath.toList())
            delete(outputDir)
            commandLine("$javaHome/bin/jlink",
                "--module-path",
                listOf("$javaHome/jmods/", runtimeClasspath.asPath, jar.archiveFile.get()).joinToString(File.pathSeparator),
                "--add-modules", moduleName,
                "--output", outputDir
            )
        }
    }
}

// to write module version into module-info, execute right after making Jar:
// "%JAVA_HOME%/bin/jar.exe" --update --module-version=1.0-SNAPSHOT --file build\libs\app-1.0-SNAPSHOT.jar -C build/classes/java/main module-info.class