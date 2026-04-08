package org.example.maxonciksaoyorcik;

public class DifficultyLevels {
    static HelloController helloController;
    public DifficultyLevels(HelloController controller) {
        this.helloController = controller;
    }
    public static int ROWS = 9;
    public static int COLS = 9;
    public static int MINES = 10;
    public static void Easy() {
        ROWS = 9;
        COLS = 9;
        MINES = 10;
        helloController.reset();
    }
    public static void Medium() {
        ROWS = 16;
        COLS = 16;
        MINES = 40;
        helloController.reset();
    }
    public static void Hard() {
        ROWS = 16;
        COLS = 30;
        MINES = 99;
        helloController.reset();
    }
}
