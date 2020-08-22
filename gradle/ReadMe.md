## Gradle example of using jlink with Kotlin

This sample project consists of a kotlin library [`modularLib`](library) and a kotlin application [`app`](app).

The library project has tests that are executed in the modular environment, accessing 
the main code as a black box, i.e. the tests are located in a separate module.

### Build instructions
   
You need to set `JAVA_HOME` environment variable to the path to JDK-9 or greater.
   
You can build or jlink the application as following:

    ./gradlew :app:build 

    ./gradlew :app:jlink
    
jlink will craft a Java distribution in `app/build/jlink` directory 
with the only modules that are required for this application.
To run the application, execute:
    
    ./app/build/jlink/bin/modularApp
