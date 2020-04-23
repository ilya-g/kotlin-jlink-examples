## Gradle example of using jlink with Kotlin

This sample project consists of a kotlin library [`modularLib`](library) and kotlin application [`app`](app).

`library` depends on automatic modules `kotlin-stdlib`, `kotlin-stdlib-jdk7/8` etc, 
while `app` prepends modular versions of these dependencies to the module path. 
This trick makes the Kotlin compiler, Java compiler and jlink find the requested kotlin standard library modules in these 
modular artifacts rather than in automatic artifacts that are listed later in the module path.

### Build instructions
   
You need to set `JAVA_HOME` environment variable to the path to JDK-9 or greater.
   
You can build or jlink the application as following:

    ./gradlew :app:build 

    ./gradlew :app:jlink
    
jlink will craft a Java distribution in `app/build/jlink` directory 
with the only modules that are required for this application.
To run the application, execute:
    
    ./app/build/jlink/bin/modularApp
