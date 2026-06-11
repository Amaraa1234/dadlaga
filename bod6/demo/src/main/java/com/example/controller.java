package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controller {

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    @FXML
    private void handleCalculate() {
        try {

            long n = Long.parseLong(inputField.getText().trim());

            if (n < 1) {
                resultLabel.setText("Алдаа: Натурал тоо биш байна!");
                return;
            }

            long sum = n * (n + 1) / 2;

            resultLabel.setText("Хариулт: " + sum);

        } catch (NumberFormatException e) {

            resultLabel.setText("Алдаа: тоо оруулна уу!");
        }
    }
}