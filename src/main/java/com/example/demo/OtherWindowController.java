package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public class OtherWindowController {

    // Перемикачі для Запитання 1
    @FXML private RadioButton answer1Button;
    @FXML private RadioButton answer1Label;
    @FXML private RadioButton answer1TextField;

    // ComboBox для запитання 2
    @FXML private ComboBox<String> comboBox;

    // TextField для Запитання 4
    @FXML private TextField answer4Field;

    // Прапорці для Запитання 5
    @FXML private CheckBox checkbox5Application;
    @FXML private CheckBox checkbox5Main;
    @FXML private CheckBox checkbox5Launcher;

    // ChoiceBox для Запитання 6
    @FXML private ChoiceBox<String> choiceBox;

    // Мітки для відображення результатів
    @FXML private Label result1;
    @FXML private Label result2;
    @FXML private Label result3;
    @FXML private Label result4;
    @FXML private Label result5;
    @FXML private Label result6;

    // ImageView для відтворення вибраного зображення
    @FXML private ImageView imageView;

    // Правильні відповіді на запитання
    private final String CORRECT_ANSWER1 = "Кнопка";
    private final String CORRECT_ANSWER2 = "javafx.controls";
    private final String CORRECT_ANSWER3 = "Стадія";
    private final String CORRECT_ANSWER4 = "javafx.application.Application";
    private final String CORRECT_ANSWER5 = "Програма";
    private final String CORRECT_ANSWER6 = "javafx.stage";

    @FXML private void initialize() {
        // Налаштувати ToggleGroup для перемикачів у Запитанні 1
        ToggleGroup group1 = new ToggleGroup();
        answer1Button.setToggleGroup(group1);
        answer1Label.setToggleGroup(group1);
        answer1TextField.setToggleGroup(group1);

        // Налаштування ComboBox для Запитання 2
        comboBox.getItems().addAll("javafx.controls", "javax.swing", "javafx.media");

        // Налаштувати ChoiceBox для Запитання 6
        choiceBox.getItems().addAll("javafx.application", "javafx.stage", "javafx.controls");
    }

    @FXML private void handleSaveAnswer1() {
        if (answer1Button.isSelected()) {
            result1.setText("Правильна відповідь! Клас для кнопок - Button.");
            result1.setStyle("-fx-font-size: 12px; color: green;");
        } else {
            result1.setText("Неправильна відповідь. Спробуйте знову.");
            result1.setStyle("-fx-font-size: 12px; color: red;");
        }
    }

    @FXML private void handleSaveAnswer2() {
        String selectedItem = comboBox.getSelectionModel().getSelectedItem();
        if (CORRECT_ANSWER2.equals(selectedItem)) {
            result2.setText("Правильна відповідь! Використовується javafx.controls.");
            result2.setStyle("-fx-font-size: 12px; color: green;");
        } else {
            result2.setText("Неправильна відповідь. Спробуйте знову.");
            result2.setStyle("-fx-font-size: 12px; color: red;");
        }
    }

    @FXML private void handleSaveAnswer3() {
        if (answer1Button.isSelected()) {
            result3.setText("Правильна відповідь! Використовується клас Стадія.");
            result3.setStyle("-fx-font-size: 12px; color: green;");
        } else {
            result3.setText("Неправильна відповідь. Спробуйте знову.");
            result3.setStyle("-fx-font-size: 12px; color: red;");
        }
    }

    @FXML private void handleSaveAnswer4() {
        String answer = answer4Field.getText();
        if (CORRECT_ANSWER4.equals(answer)) {
            result4.setText("Правильна відповідь! Головний клас програми - javafx.application.Application.");
            result4.setStyle("-fx-font-size: 12px; color: green;");
        } else {
            result4.setText("Неправильна відповідь. Спробуйте знову.");
            result4.setStyle("-fx-font-size: 12px; color: red;");
        }
    }

    @FXML private void handleSaveAnswer5() {
        if (checkbox5Application.isSelected() && !checkbox5Main.isSelected() && !checkbox5Launcher.isSelected()) {
            result5.setText("Правильна відповідь! Головний клас JavaFX - Application.");
            result5.setStyle("-fx-font-size: 12px; color: green;");
        } else {
            result5.setText("Неправильна відповідь. Спробуйте знову.");
            result5.setStyle("-fx-font-size: 12px; color: red;");
        }
    }

    @FXML private void handleSaveAnswer6() {
        String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
        if (CORRECT_ANSWER6.equals(selectedItem)) {
            result6.setText("Правильна відповідь! Для створення вікон використовуйте javafx.stage.");
            result6.setStyle("-fx-font-size: 12px; color: green;");
        } else {
            result6.setText("Неправильна відповідь. Спробуйте знову.");
            result6.setStyle("-fx-font-size: 12px; color: red;");
        }
    }

    @FXML private void handleChangeImage(ActionEvent event) {
        // Створення FileChooser, щоб дозволити користувачеві вибрати зображення
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файли зображення", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Відкриття діалогового вікна вибору файлу
        File selectedFile = fileChooser.showOpenDialog(null);

        // Якщо файл був вибраний, встановлюємо його як вихідне зображення
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    @FXML private void handleCloseWindow(ActionEvent event) {
        // Отримати сцену (вікно) і закрити її
        Stage stage = (Stage) result1.getScene().getWindow();
        stage.close();
    }
}
