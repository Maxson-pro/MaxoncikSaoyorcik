package org.example.maxonciksaoyorcik;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;
import java.util.Random;

public class HelloController {
    @FXML public GridPane pole;
    @FXML public Button btnReset;
    public Button[][] knopli = new Button[9][9];
    public int[][] matrix = new int[9][9];
    public boolean gameOver = false;
    Metods metods = new Metods(this);


    @FXML
    public void reset() {
        gameOver = false;
        btnReset.setText("Начать заново 😊");
        btnReset.setStyle("");
        pole.getChildren().clear();
        for (int i = 0; i < 9; i++) {
            for (int a = 0; a < 9; a++) {
                matrix[i][a] = 0;
            }
        }
        Random random = new Random();
        int m = 0;
        while (m < 10) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (matrix[x][y] != -1) {
                matrix[x][y] = -1;
                m++;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int a = 0; a < 9; a++) {
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
        for (int i = 0; i < 9; i++) {
            for (int a = 0; a < 9; a++) {
                Button b = new Button();
                b.setPrefSize(40, 40);
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
    }

    @FXML
    public void initialize() {
        reset();
    }
}