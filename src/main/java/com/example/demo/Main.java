package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Завантаження FXML-файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/laba1.fxml"));
        Scene scene = new Scene(loader.load());

        // Підключення CSS-файлу
        scene.getStylesheets().add(getClass().getResource("/com/example/demo/Bag.css").toExternalForm());

        // Налаштування сцени
        primaryStage.setTitle("Адресна книга");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX застосунку
    }
}
