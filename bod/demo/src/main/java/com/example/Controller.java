package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label lblResult;

    @FXML
    private TextField txtNum1;

    @FXML
    private TextField txtNum2;

    @FXML
    void onCalculateClick(ActionEvent event) {
        try {

            String str1 = txtNum1.getText();
            String str2 = txtNum2.getText();

            double num1 = Double.parseDouble(str1);
            double num2 = Double.parseDouble(str2);

            double sum = num1 + num2;

            lblResult.setText("Хариу: " + sum);

        } catch (NumberFormatException e) {

            lblResult.setText("Алдаа: Зөвхөн тоо оруулна уу!");
        }
    }
}
