module org.test.modularLib {
    requires transitive kotlin.stdlib;
    requires kotlin.stdlib.jdk8;

    exports org.test.modularLib;
}