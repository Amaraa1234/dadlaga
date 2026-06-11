package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controller {

    @FXML
    private TextField inputField;

    @FXML
    private Label outField;

    // Шалгах товчлуур дээр дарах үед ажиллах функц
    @FXML
    void handleCalculate(ActionEvent event) {
        String inputText = inputField.getText().trim();
        
        // Оролтын талбар хоосон эсэхийг шалгах
        if (inputText.isEmpty()) {
            outField.setText("Та тоо оруулна уу!");
            return;
        }

        try {
            int n = Integer.parseInt(inputText);

            // Бодлогын хязгаарлалт: 1 <= n <= 1000
            if (n < 1 || n > 1000) {
                outField.setText("Уучлаарай, 1-ээс 1000-ын хооронд\nтоо оруулна уу.");
                return;
            }

            // 1-ээс 1000 хүртэлх бүх азтай тоонуудын жагсаалт
            int[] luckyNumbers = {4, 7, 44, 47, 74, 77, 444, 447, 474, 477, 744, 747, 774, 777};
            
            boolean isAlmostLucky = false;

            // Өгөгдсөн тоо аль нэг азтай тоонд хуваагдаж байгаа эсэхийг шалгах
            for (int lucky : luckyNumbers) {
                if (n % lucky == 0) {
                    isAlmostLucky = true;
                    break; // Нэг л тоонд хуваагдсан бол цааш шалгах шаардлагагүй
                }
            }

            // Үр дүнг хэвлэх
            if (isAlmostLucky) {
                outField.setText("YES");
            } else {
                outField.setText("NO");
            }

        } catch (NumberFormatException e) {
            // Хэрэв хэрэглэгч тоо биш, үсэг эсвэл тэмдэгт оруулбал
            outField.setText("Зөвхөн бүхэл тоо оруулна уу!");
        }
    }
}
