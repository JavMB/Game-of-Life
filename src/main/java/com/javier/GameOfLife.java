package com.javier;


import java.util.Arrays;

public class GameOfLife {
    boolean[][] vida;

    public GameOfLife() {
        this.vida = new boolean[Config.filasDeseadas][Config.columnasDeseadas];
    }


    void seedManual(int x, int y) {
        vida[x][y] = true;
    }

    void seedAleatoria() {
        for (int i = 0; i < vida.length; i++) {
            for (int j = 0; j < vida[i].length; j++) {
                vida[i][j] = Math.random() < 0.1;
            }

        }
    }

    void reset() {
        for (boolean[] booleans : vida) {
            Arrays.fill(booleans, false);
        }
    }

    public void update() {
        vida = recorrido();
    }

    public boolean[][] recorrido() {
        boolean[][] nueva = new boolean[vida.length][vida[0].length];
        int contadorVivas;


        for (int i = 0; i < vida.length; i++) {
            for (int j = 0; j < vida[i].length; j++) {
                contadorVivas = contarVecinas(i, j);

                nueva[i][j] = determinar(vida[i][j], contadorVivas);
            }
        }


        return nueva;
    }

    // ha costado...
    private int contarVecinas(int i, int j) {
        int contador = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (k < 0 || l < 0 || k >= vida.length || l >= vida[0].length)
                    continue;
                if (k == i && l == j)
                    continue;
                if (vida[k][l]) contador++;
            }
        }
        return contador;
    }


    public boolean determinar(boolean celula, int vivas) {
        if (celula) {
            return vivas == 2 || vivas == 3;

        } else {
            return vivas == 3;

        }
    }


}