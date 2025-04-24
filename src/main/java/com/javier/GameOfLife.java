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

        for (int i = 0; i < vida.length; i++) {
            for (int j = 0; j < vida[i].length; j++) {
                nueva[i][j] = juego(i, j);
            }

        }

        return nueva;
    }

    //TODO contar vivas,teniendo en cuenta bordes esquinas null
    private boolean juego(int i, int j) {
        boolean celula = vida[i][j];
        int contadorVivas = 0;



        return determinar(celula, contadorVivas);
    }


    public boolean determinar(boolean celula, int vivas) {
        if (celula) {
            return vivas == 2 || vivas == 3;

        } else {
            return vivas == 3;

        }
    }


}