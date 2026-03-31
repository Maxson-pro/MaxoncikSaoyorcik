package org.example.maxonciksaoyorcik;

public class Metods {
    HelloController helloController;
    public Metods(HelloController controller) {
        this.helloController = controller;
    }
            public void proveritPobedu() {
                int zakrito = 0;
                for (int i = 0; i < 9; i++) {
                    for (int a = 0; a < 9; a++) {
                        if (!helloController.knopli[i][a].isDisable()) zakrito++;
                    }
                }
                if (zakrito == 10 && !helloController.gameOver) {
                    helloController.gameOver = true;
                    helloController.btnReset.setText("Ты выиграл");
                    helloController.btnReset.setStyle("-fx-background-color: #90EE90;");
                }
            }

            public void proigral() {
                helloController.gameOver = true;
                helloController.btnReset.setText("Ты проиграл");
                for (int i = 0; i < 9; i++) {
                    for (int a = 0; a < 9; a++) {
                        if ( helloController.matrix[i][a] == -1) {
                            helloController.knopli[i][a].setText("💣");
                            helloController.knopli[i][a].setStyle("-fx-background-color: red;");
                        }
                    }
                }
            }

            public boolean isMina(int r, int c) {
                if (r >= 0 && r < 9 && c >= 0 && c < 9) {
                    return  helloController.matrix[r][c] == -1;
                }
                return false;
            }

            public void otkril(int r, int c) {
                if (r < 0 || r >= 9 || c < 0 || c >= 9 ||  helloController.knopli[r][c].isDisable()) return;
                helloController.knopli[r][c].setDisable(true);
                helloController.knopli[r][c].setStyle("-fx-background-color: white; -fx-opacity: 1; -fx-text-fill: black;");
                int cifra =  helloController.matrix[r][c];
                if (cifra != 0) {
                    helloController.knopli[r][c].setText("" + cifra);
                } else {
                    otkril(r - 1, c); otkril(r + 1, c);
                    otkril(r, c - 1); otkril(r, c + 1);
                    otkril(r - 1, c - 1); otkril(r - 1, c + 1);
                    otkril(r + 1, c - 1); otkril(r + 1, c + 1);
                }
                proveritPobedu();
            }
        }



