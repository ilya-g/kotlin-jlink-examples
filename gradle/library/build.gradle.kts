import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
     `java-library`
    kotlin("jvm")
}

base.archivesBaseName = "modularLib"
val moduleName by extra("org.test.modularLib")

dependencies {
    api(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_9
    targetCompatibility = JavaVersion.VERSION_1_9
    modularity.inferModulePath.set(true)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "9"
}

val integTest: SourceSet by sourceSets.creating
val integTestImplementation by configurations

dependencies {
    integTestImplementation(project)
    integTestImplementation(kotlin("test-junit"))
    integTestImplementation("junit:junit:4.13")
}

val integTestJarTask = tasks.register<Jar>(integTest.jarTaskName) {
    archiveClassifier.set("integration-tests")
    from(integTest.output)
}
val integTestTask = tasks.register<Test>("integTest") {
    testClassesDirs = integTest.output.classesDirs
    // Make sure we run the 'Jar' containing the tests (and not just the 'classes' folder) so that test resources are also part of the test module
    classpath = configurations[integTest.runtimeClasspathConfigurationName] + files(integTestJarTask)
}

tasks {
    compileJava {
        inputs.property("moduleName", moduleName)
        options.compilerArgs = listOf(
            "--patch-module", "$moduleName=${sourceSets.main.get().output.asPath}"
        )
    }
    check { dependsOn(integTestTask) }
}
