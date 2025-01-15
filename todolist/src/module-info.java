module todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swt;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires java.activation;
    requires java.mail;

    opens todolist to javafx.fxml;
    exports todolist;
}
