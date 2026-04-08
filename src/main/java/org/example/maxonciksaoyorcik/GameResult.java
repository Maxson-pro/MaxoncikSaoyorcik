package org.example.maxonciksaoyorcik;

public class GameResult {
    HelloController helloController;

    public GameResult(HelloController controller) {
        this.helloController = controller;
    }

    private static final String YOU_WIN = "Ты выиграл";
    private static final String BOMB = "💣";
    private static final String YOU_LOST = "Ты проиграл";
    public void victory() {
        int currentClosed = 0;
        for (int i = 0; i < DifficultyLevels.ROWS; i++) {
            for (int a = 0; a < DifficultyLevels.COLS; a++) {
                if (!helloController.storage[i][a].isDisable()) currentClosed++;
            }
        }
        if (currentClosed == DifficultyLevels.MINES && !helloController.gameOver) {
            helloController.gameOver = true;
            helloController.btnReset.setText(YOU_WIN);
            helloController.btnReset.setStyle("-fx-background-color: #90EE90;");
        }
    }

    public void lost() {
        helloController.gameOver = true;
        helloController.btnReset.setText(YOU_LOST);
        for (int i = 0; i < DifficultyLevels.ROWS; i++) {
            for (int a = 0; a < DifficultyLevels.COLS; a++) {
                if (helloController.matrix[i][a] == -1) {
                    helloController.storage[i][a].setText(BOMB);
                    helloController.storage[i][a].setStyle("-fx-background-color: red; -fx-font-size: 6px;");
                }
            }
        }
    }
    void closest() {
        for (int i = 0; i < DifficultyLevels.ROWS; i++) {
            for (int a = 0; a < DifficultyLevels.COLS; a++) {
                if (helloController.matrix[i][a] == -1) continue;

                int count = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (helloController.aboutMines.isMina(i + x, a + y)) count++;
                    }
                }
                helloController.matrix[i][a] = count;
            }
        }
    }
}
