module org.test.modularApp {
    requires transitive kotlin.stdlib;
    requires org.test.modularLib;

    exports org.test.modularApp;
}