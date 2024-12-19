package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private TableView<Contact> dataTable;
    @FXML
    private TableColumn<Contact, String> columnName;
    @FXML
    private TableColumn<Contact, String> columnPhone;
    @FXML
    private TextField searchField;
    @FXML
    private Label counterLabel;
    @FXML
    private TextField displayedName;

    @FXML
    private TextField displayedPhone;


    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        dataTable.setItems(contactList);

        // Слухач для відображення вибраного контакту
        dataTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayedName.setText(newSelection.getName());
                displayedPhone.setText(newSelection.getPhone());
            } else {
                displayedName.clear();
                displayedPhone.clear();
            }
        });
    }

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> fullNameColumn;
    @FXML
    private TableColumn<Person, String> phoneNumberColumn;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneNumberField;

    private ObservableList<Person> personList;




    @FXML
    private void handleAdd(ActionEvent event) {
        Dialog<Contact> dialog = createContactDialog(null);
        dialog.showAndWait().ifPresent(contact -> {
            contactList.add(contact);
            updateCounter();
        });
    }



    @FXML
    private void handleEdit(ActionEvent event) {
        Contact selectedContact = dataTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            Dialog<Contact> dialog = createContactDialog(selectedContact);
            dialog.showAndWait().ifPresent(contact -> {
                selectedContact.setName(contact.getName());
                selectedContact.setPhone(contact.getPhone());
                dataTable.refresh();
            });
        } else {
            showAlert("Редагування", "Оберіть запис для редагування.");
        }
    }

    // Метод для роботи з даними, переданими з нового вікна
    public void addOtherDescription(String description) {
        System.out.println("Опис: " + description); // Тут можна обробити дані (наприклад, додати в список)
    }


    @FXML
    private void handleDelete(ActionEvent event) {
        Contact selectedContact = dataTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contactList.remove(selectedContact);
            updateCounter();
        } else {
            showAlert("Видалення", "Оберіть запис для видалення.");
        }
    }
    @FXML
    private void handleSearch() {
        String query = searchField.getText().toLowerCase();
        if (query.isEmpty()) {
            dataTable.setItems(contactList); // Відобразити всі записи
            return;
        }

        ObservableList<Contact> filteredList = FXCollections.observableArrayList();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(query) || contact.getPhone().contains(query)) {
                filteredList.add(contact);
            }
        }

        if (filteredList.isEmpty()) {
            showAlert("Пошук", "Записи не знайдено.");
        }

        dataTable.setItems(filteredList);
    }
    @FXML
    private void handleSearchClear() {
        searchField.clear();
        dataTable.setItems(contactList);
    }



    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<Contact> filteredList = contactList.filtered(contact ->
                contact.getName().toLowerCase().contains(searchText) ||
                        contact.getPhone().contains(searchText)
        );
        dataTable.setItems(filteredList);
    }


    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }



    private Dialog<Contact> createContactDialog(Contact contact) {
        Dialog<Contact> dialog = new Dialog<>();
        dialog.setTitle(contact == null ? "Додати запис" : "Редагувати запис");
        dialog.setHeaderText(null);

        TextField nameField = new TextField(contact != null ? contact.getName() : "");
        nameField.setPromptText("ПІБ");
        TextField phoneField = new TextField(contact != null ? contact.getPhone() : "");
        phoneField.setPromptText("Телефон");

        VBox content = new VBox(10, new Label("ПІБ:"), nameField, new Label("Телефон:"), phoneField);
        dialog.getDialogPane().setContent(content);

        ButtonType okButtonType = ButtonType.OK;
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == okButtonType) {
                return new Contact(nameField.getText(), phoneField.getText());
            }
            return null;
        });

        return dialog;
    }
    @FXML
    private void handleOpenOtherWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/otherWindow.fxml"));
            Parent root = loader.load();

            // Отримуємо контролер для іншого вікна
            OtherController otherController = loader.getController();
            otherController.setHelloController(this); // Передаємо дані контролеру

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Робить вікно модальним
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleOpenOtherWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/otherWindow.fxml"));
            Parent root = loader.load();

            // Отримуємо контролер для іншого вікна
            OtherController otherController = loader.getController();

            // Додаємо тестові контакти
            otherController.addContact("Іван Іванов", "123456789");
            otherController.addContact("Петро Петренко", "987654321");

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Робить вікно модальним
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onAddButtonClicked() {
        String fullName = fullNameField.getText();
        String phoneNumber = phoneNumberField.getText();

        if (!fullName.isEmpty() && !phoneNumber.isEmpty()) {
            personList.add(new Person(fullName, phoneNumber)); // Додаємо нового користувача до списку
            clearFields();
        }
    }
    @FXML
    private void handleOther() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OtherWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Інше вікно");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void onEditButtonClicked() {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            selectedPerson.setFullName(fullNameField.getText());
            selectedPerson.setPhoneNumber(phoneNumberField.getText());
            tableView.refresh(); // Оновлюємо таблицю
            clearFields();
        }
    }
    @FXML
    private void onDeleteButtonClicked() {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            personList.remove(selectedPerson);
        }
    }
    public void clearFields() {
        fullNameField.clear();
        phoneNumberField.clear();
    }
    @FXML
    private void handleMusic(ActionEvent event) {
        showAlert("Музика", "Функція відтворення музики ще не реалізована.");
    }



    private void updateCounter() {
        counterLabel.setText("Кількість записів: " + contactList.size());
    }

    public void addContact(String name, String phone) {
    }

    public void handleSave(ActionEvent actionEvent) {
    }

    public void handleHelloWorld(ActionEvent actionEvent) {
    }



}
