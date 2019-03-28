import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Cannot use java-library plugin for building modular libs, see details in the issue GRADLE-xxxx
    // `java-library`
    kotlin("jvm")
}

base.archivesBaseName = "modularLib"
val moduleName by extra("org.test.modularLib")


dependencies {
    api(kotlin("stdlib-jdk8"))
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
}
