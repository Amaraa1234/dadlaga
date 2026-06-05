package com.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleIntegerProperty status; // № (ID)
    private final SimpleStringProperty name; // ner
    private final SimpleStringProperty id; // kod
    private final SimpleIntegerProperty score; // onoo

    public Student(int status, String name, String id, int score) {
        this.status = new SimpleIntegerProperty(status);
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.score = new SimpleIntegerProperty(score);
    }

    public int getStatus() {
        return status.get();
    }

    public SimpleIntegerProperty statusProperty() {
        return status;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}