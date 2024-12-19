package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class OtherController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Contact> tableView;

    @FXML
    private TableColumn<Contact, String> nameColumn;

    @FXML
    private TableColumn<Contact, String> phoneColumn;

    // Список картинок для зміни фону
    private final String[] backgrounds = {
            "/com/example/demo/images/bg1.jpg",
            "/com/example/demo/images/bg2.jpg",
            "/com/example/demo/images/bg3.jpg"
    };

    private int currentBackgroundIndex = 0;

    // Дані для таблиці
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Налаштовуємо колонки таблиці
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // Прив'язуємо список контактів до таблиці
        tableView.setItems(contacts);

        // Встановлюємо початковий фон
        updateBackground();
    }

    // Метод для зміни фону
    @FXML
    private void handleChangeBackground() {
        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
        updateBackground();
    }

    private void updateBackground() {
        String backgroundImage = backgrounds[currentBackgroundIndex];
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover;");
    }

    // Метод для додавання контакту
    public void addContact(String name, String phone) {
        contacts.add(new Contact(name, phone));
    }

    public void setHelloController(HelloController helloController) {
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
    }
}
