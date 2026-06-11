package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class controller {

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    @FXML
    void handleCalculate(ActionEvent event) {

        try {

            String text = inputField.getText().trim();

            String[] tokens = text.split("\\s+");

            if (tokens.length < 2) {
                resultLabel.setText("Хариулт: Хоёр тоо оруулна уу!");
                return;
            }

            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);

            int maxResult = Math.max(a, b);
            resultLabel.setText("Хариулт: " + maxResult);

        } catch (NumberFormatException e) {

            resultLabel.setText("Хариулт: Зөвхөн бүхэл тоо оруулна уу!");
        }
    }
}
