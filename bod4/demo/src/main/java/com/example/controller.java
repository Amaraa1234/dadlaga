package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controller {

    @FXML
    private TextField hoursField;

    @FXML
    private TextField minutesField;

    @FXML
    private TextField secondsField;

    @FXML
    private Label resultLabel;

    @FXML
    private void handleCalculate() {
        try {
            // Хэрэглэгч утга оруулаагүй (хоосон) бол 0 гэж авна
            int hours = hoursField.getText().trim().isEmpty() ? 0 : Integer.parseInt(hoursField.getText().trim());
            int minutes = minutesField.getText().trim().isEmpty() ? 0 : Integer.parseInt(minutesField.getText().trim());
            int seconds = secondsField.getText().trim().isEmpty() ? 0 : Integer.parseInt(secondsField.getText().trim());

            // Секунд рүү шилжүүлэх тооцоолол
            int totalSeconds = (hours * 3600) + (minutes * 60) + seconds;

            // Үр дүнг зураг дээрх шиг форматтай харуулах
            resultLabel.setText("Үр дүн: " + totalSeconds + " секунд");

        } catch (NumberFormatException e) {
            resultLabel.setText("Үр дүн: Алдаа! Зөвхөн тоо оруулна уу.");
        }
    }
}
