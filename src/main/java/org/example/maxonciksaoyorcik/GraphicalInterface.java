package org.example.maxonciksaoyorcik;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class GraphicalInterface {
    HelloController helloController;

    public GraphicalInterface(HelloController controller) {
        this.helloController = controller;
    }
   public void print() {
        for (int i = 0; i < DifficultyLevels.ROWS; i++) {
            for (int a = 0; a < DifficultyLevels.COLS; a++) {
                Button b = new Button();
                double size;
                if (DifficultyLevels.ROWS == 9) {
                    size = 35;
                } else {
                    size = 25;
                }
                b.setPrefSize(size, size);
                b.setMinSize(size, size);
                b.setMaxSize(size, size);
                b.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-padding: 0;");
                b.setPadding(new javafx.geometry.Insets(0));
                int row = i;
                int col = a;
                b.setOnMouseClicked(event -> {
                    if (helloController.gameOver) return;
                    if (event.getButton() == MouseButton.SECONDARY) {
                        if (b.getText().equals(helloController.SYMBOL)) b.setText("");
                        else if (b.getText().equals("")) b.setText(helloController.SYMBOL);
                    }
                    if (event.getButton() == MouseButton.PRIMARY && !b.getText().equals(helloController.SYMBOL)) {
                        if (helloController.matrix[row][col] == -1) helloController.gameResult.lost();
                        else GraphicalInterface.open(row, col);
                    }
                });

                helloController.storage[i][a] = b;
                helloController.pole.add(b, a, i);
            }
        }
    }
    public static void open(int r, int c) {
        if (r < 0 || r >= DifficultyLevels.ROWS || c < 0 || c >= DifficultyLevels.COLS) return;
        if (HelloController.storage[r][c].isDisable()) return;
        HelloController.storage[r][c].setDisable(true);
        HelloController.storage[r][c].setStyle("-fx-background-color: white; -fx-opacity: 1; -fx-text-fill: black; -fx-font-size: 9px; -fx-font-weight: bold;");
        int number =  HelloController.matrix[r][c];
        if (number != 0) {
            HelloController.storage[r][c].setText("" + number);
        } else {
            open(r - 1, c);
            open(r + 1, c);
            open(r, c - 1);
            open(r, c + 1);
            open(r - 1, c - 1);
            open(r - 1, c + 1);
            open(r + 1, c - 1);
            open(r + 1, c + 1);
        }
        HelloController.gameResult.victory();
    }
}



