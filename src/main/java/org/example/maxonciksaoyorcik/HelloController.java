package org.example.maxonciksaoyorcik;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;
import java.util.Random;

public class HelloController {
    @FXML public GridPane pole;
    @FXML public Button btnReset;

    public int rows = 9;
    public int cols = 9;
    public int mines = 10;
    public Button[][] knopli;
    public int[][] matrix;
    public boolean gameOver = false;
    Metods metods;
    public void Easy() {
        rows = 9;
        cols = 9;
        mines = 10;
        reset();
    }
    public void Medium() {
        rows = 16;
        cols = 16;
        mines = 40;
        reset();
    }
    public void Hard() {
        rows = 16;
        cols = 30;
        mines = 99;
        reset();
    }


    @FXML
    public void reset() {
        gameOver = false;
        btnReset.setText("Начать заново");
        btnReset.setStyle("");
        pole.getChildren().clear();
        knopli = new Button[rows][cols];
        matrix = new int [rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int a = 0; a < cols; a++) {
                matrix[i][a] = 0;
            }
        }
        Random random = new Random();
        int m = 0;
        while (m < mines) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (matrix[x][y] != -1) {
                matrix[x][y] = -1;
                m++;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int a = 0; a < cols; a++) {
                if (matrix[i][a] == -1) continue;
                int count = 0;
                if (metods.isMina(i - 1, a - 1)) count++;
                if (metods.isMina(i - 1, a))     count++;
                if (metods.isMina(i - 1, a + 1)) count++;
                if (metods.isMina(i, a - 1))     count++;
                if (metods.isMina(i, a + 1))     count++;
                if (metods.isMina(i + 1, a - 1)) count++;
                if (metods.isMina(i + 1, a))     count++;
                if (metods.isMina(i + 1, a + 1)) count++;
                matrix[i][a] = count;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int a = 0; a < cols; a++) {
                Button b = new Button();
                double size = 25;
                if (rows == 9) size = 35;

                b.setPrefSize(size, size);
                b.setMinSize(size, size);
                b.setMaxSize(size, size);
                b.setPadding(new javafx.geometry.Insets(0));

                b.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-padding: 0;");

                int row = i;
                int col = a;

                b.setOnMouseClicked(event -> {
                    if (gameOver) return;
                    if (event.getButton() == MouseButton.SECONDARY) {
                        if (b.getText().equals("🚩")) b.setText("");
                        else if (b.getText().equals("")) b.setText("🚩");
                    }
                    else if (event.getButton() == MouseButton.PRIMARY) {
                        if (b.getText().equals("🚩")) return;
                        if (matrix[row][col] == -1) {
                            metods.proigral();
                        } else {
                            metods.otkril(row, col);
                        }
                    }
                });
                knopli[i][a] = b;
                pole.add(b, a, i);
            }
        }
        if (pole.getScene() != null && pole.getScene().getWindow() != null) {
            pole.getScene().getWindow().sizeToScene();
        }
    }

    @FXML
    public void initialize() {
        metods = new Metods(this);
        reset();
    }
}