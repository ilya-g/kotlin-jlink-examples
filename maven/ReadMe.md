## Maven example of using jlink with Kotlin

This sample project consists of a kotlin library [`mavenModularLib`](library) and kotlin application [`mavenModularApp`](app).

Both library and application depend on automatic module artifacts of the Kotlin standard library: `kotlin-stdlib`, `kotlin-stdlib-jdk7/8` etc.

There's a separate project [`mavenModular-jlinked`](jlinked), that executes jlink goal. It has to exclude all non-modular jars from 
transitive dependencies and add modular jars instead.


### Build instructions

You need to set `JAVA_HOME` environment variable to the path to JDK-9 or greater, otherwise jlink won't work.
Then you can run:

    mvn clean package
    
jlink will craft a Java distribution in `jlinked/target/maven-jlink` directory 
with the only modules that are required for this application.
To run the application, execute:
    
    jlinked/target/maven-jlink/bin/java -m org.test.modularApp/org.test.modularApp.HelloKt