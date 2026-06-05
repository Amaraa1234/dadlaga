package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    @FXML
    void handleCalculate(ActionEvent event) {
        try {

            int number = Integer.parseInt(inputField.getText());

            if (number > 10) {
                resultLabel.setText("хариулт : YES");
            } else if (number < 10) {
                resultLabel.setText("хариулт : NO");
            } else {
                resultLabel.setText("хариулт : =");
            }
        } catch (NumberFormatException e) {

            resultLabel.setText("хариулт : Зөвхөн тоо оруулна уу!");
        }
    }
}
