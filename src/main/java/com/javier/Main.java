package com.javier;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Juego de la Vida");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        GameOfLife juego = new GameOfLife();

        GameCanvas canvas = new GameCanvas(juego);
        canvas.setPreferredSize(new Dimension(1920, 1080));
        ventana.add(canvas);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        new Thread(canvas).start(); //  ejecución en segundo plano
    }
}

