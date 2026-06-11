package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Random;

public class Controller { // Классын нэрийг том үсгээр эхлүүлэв

    @FXML
    private Label playerALabel;

    @FXML
    private Label playerBLabel;

    @FXML
    private Label resultLabel;

    private final Random random = new Random();

    @FXML
    private void onStartGame() {
        // 1-ээс 6 хүртэлх санамсаргүй тоо үүсгэх
        int p1Score = random.nextInt(6) + 1;
        int p2Score = random.nextInt(6) + 1;

        // Дэлгэц дээрх зөөлөн ягаан нүднүүдэд тоог харуулах
        playerALabel.setText(String.valueOf(p1Score));
        playerBLabel.setText(String.valueOf(p2Score));

        // Хэн нь ялсныг шалгах логик
        if (p1Score > p2Score) {
            resultLabel.setText("1-р тоглогч яллаа! 🎉");
        } else if (p2Score > p1Score) {
            resultLabel.setText("2-р тоглогч яллаа! 🎉");
        } else {
            resultLabel.setText("Тэнцлээ! 🤝");
        }
    }
}