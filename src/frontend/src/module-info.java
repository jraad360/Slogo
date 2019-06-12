module frontend {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires backend;

    exports frontend_external;
}