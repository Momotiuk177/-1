module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}