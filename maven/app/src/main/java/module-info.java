module org.test.modularApp {
    requires transitive kotlin.stdlib;
    requires kotlin.stdlib.jdk8;
    requires org.test.modularLib;

    exports org.test.modularApp;
}