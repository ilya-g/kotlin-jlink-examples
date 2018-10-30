## Gradle example of using jlink with Kotlin

This sample project consists of a kotlin library [`modularLib`](library) and kotlin application [`app`](app).

`library` depends on automatic modules `kotlin-stdlib`, `kotlin-stdlib-jdk7/8` etc, 
while `app` prepends modular versions of these dependencies to the module path. 
This trick makes the Kotlin compiler, Java compiler and jlink find the requested kotlin standard library modules in these 
modular artifacts rather than in automatic artifacts that are listed later in the module path.

**Caveat**: there's a problem in Gradle with depending on a library project in modular Java environment. 
To workaround that we have to publish that library artifact first to mavenLocal, then depend on that library in mavenLocal 
by its artifact coordinates.

Therefore to build this project, first execute:

    ./gradlew :library:publishToMavenLocal
    
Then you can build or jlink the application:

    ./gradlew :app:build 

    ./gradlew :app:jlink
    
jlink will craft a Java distribution in `app/build/jlink` directory 
with the only modules that are required for this application.
To run the application, execute:
    
    ./app/build/jlink/bin/java -m org.test.modularApp/org.test.modularApp.HelloKt
