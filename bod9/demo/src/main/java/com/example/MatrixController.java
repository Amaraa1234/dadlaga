package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.Random;

public class MatrixController {

    @FXML
    private Label randomNumberLabel;

    @FXML
    private Button spinButton;

    @FXML
    private Label spinResultLabel;

    @FXML
    private Label resultLabel;

    private final Random random = new Random();

    @FXML
    void onSpinClicked(ActionEvent event) {

        spinButton.setDisable(true);
        spinResultLabel.setText("Тоог сонгож байна...");
        resultLabel.setText("...");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            int currentRandom = random.nextInt(9) + 1; // 1-ээс 9 хооронд тоо
            randomNumberLabel.setText(String.valueOf(currentRandom));
        }));

        timeline.setCycleCount(40);

        timeline.setOnFinished(e -> {
            spinButton.setDisable(false);

            int finalSpinValue = random.nextInt(9) + 1;
            randomNumberLabel.setText(String.valueOf(finalSpinValue));
            spinResultLabel.setText("Гарсан тоо: " + finalSpinValue);

            int n = finalSpinValue;
            int m = finalSpinValue;
            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = random.nextInt(101);
                }
            }

            int minCost = findMinPathCost(grid, n, m);
            resultLabel.setText("Хамгийн бага дүн: " + minCost);
        });

        timeline.play();
    }

    private int findMinPathCost(int[][] grid, int n, int m) {
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }
}