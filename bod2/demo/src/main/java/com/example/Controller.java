package com.example;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txtNum1;

    @FXML
    private TextField txtNum2;

    @FXML
    private Label lblResult;

    // Энэ функцийн нэр FXML дээрхтэй яг ижил байх ёстой
    @FXML
    public void onCalculateClick(ActionEvent event) {
        try {
            double a = Double.parseDouble(txtNum1.getText());
            double b = Double.parseDouble(txtNum2.getText());

            if (a <= 0 || b <= 0) {
                lblResult.setText("Зөвхөн эерэг тоо оруулна уу!");
                return;
            }

            double arithmeticMean = (a + b) / 2.0;
            double geometricMean = Math.sqrt(a * b);

            // Дэлгэцэнд илүү ойлгомжтой харуулах формат
            String resultText = String.format("Арифметик дундаж: %.2f\nГеометр дундаж: %.2f", arithmeticMean,
                    geometricMean);

            lblResult.setText(resultText);

        } catch (NumberFormatException e) {
            lblResult.setText("Тоон утга зөв оруулна уу!");
        }
    }
}