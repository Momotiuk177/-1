package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty phoneNumber;

    public Person(String fullName, String phoneNumber) {
        this.fullName = new SimpleStringProperty(fullName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }
}
