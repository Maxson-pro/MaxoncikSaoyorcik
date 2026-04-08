package org.example.maxonciksaoyorcik;

import java.util.Random;

public class AboutMines {
    HelloController helloController;

    public AboutMines(HelloController controller) {
        this.helloController = controller;
    }

    public void mines() {
        Random random = new Random();
          int m = 0;
        while (m < DifficultyLevels.MINES) {
            int x = random.nextInt(DifficultyLevels.ROWS);
              int y = random.nextInt(DifficultyLevels.COLS);
            if (helloController.matrix[x][y] != -1) {
                helloController.matrix[x][y] = -1;
                m++;
            }
        }
    }
    private boolean inBounds(int row, int col) {
        return row >= 0 &&
                row < DifficultyLevels.ROWS &&
                col >= 0 &&
                col < DifficultyLevels.COLS;
    }
    public boolean isMina(int r, int c) {
        return inBounds(r, c) && helloController.matrix[r][c] == -1;
    }
}