package org.example.maxonciksaoyorcik;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML public GridPane pole;
    @FXML public Button btnReset;
    private static final String AGAIN = "Начать заново";
    public static final String SYMBOL = "🚩";
DifficultyLevels difficultyLevels;
    public static Button[][] storage;
    public static int[][] matrix;
    public boolean gameOver = false;
    GraphicalInterface graphicalInterface;
    static GameResult gameResult;
    AboutMines aboutMines;
    @FXML public void Easy() {
        DifficultyLevels.Easy();
    }
    @FXML public void Medium() {
        DifficultyLevels.Medium();
    }
    @FXML public void Hard() {
        DifficultyLevels.Hard();
    }
    private void preparation() {
        gameOver = false;
        btnReset.setText(AGAIN);
        btnReset.setStyle("");
        pole.getChildren().clear();

        storage = new Button[DifficultyLevels.ROWS][DifficultyLevels.COLS];
        matrix = new int[DifficultyLevels.ROWS][DifficultyLevels.COLS];
    }
    @FXML
    public void reset() {
        preparation();
        aboutMines.mines();
        gameResult.closest();
        graphicalInterface.print();
        if (pole.getScene() != null && pole.getScene().getWindow() != null) {
            pole.getScene().getWindow().sizeToScene();
        }
    }

    @FXML
    public void initialize() {
         difficultyLevels = new DifficultyLevels(this);
        aboutMines = new AboutMines(this);
        gameResult = new GameResult(this);
        graphicalInterface = new GraphicalInterface(this);
        reset();
    }
}