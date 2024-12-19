package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class HelloWorldButtonController {

    // Обробник кнопки Hello World
    @FXML
    private void handleHelloWorld(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hello World");
        alert.setHeaderText(null);
        alert.setContentText("Hello World!");
        alert.showAndWait();
    }
}
