package com.javier;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;


public class GameCanvas extends Canvas implements Runnable {
    private GameOfLife juego;
    private BufferStrategy bs;
    private boolean simulando;

    public GameCanvas(GameOfLife juego) {
        this.juego = juego;
        simulando = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                manejarClick(e);
            }
        });

    }

    @Override
    public void run() {
        createBufferStrategy(2);
        bs = getBufferStrategy();

        while (true) {
            if (simulando) {
                juego.update();
            }

            render();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void pintarCeldas(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < juego.vida.length; i++) {
            for (int j = 0; j < juego.vida[i].length; j++) {
                if (juego.vida[i][j]) {
                    g.fillRect(j * Config.TAM_CELDA, i * Config.TAM_CELDA, Config.tamCeldaAncho, Config.tamCeldaAlto);

                }

            }

        }


    }


    public void render() {
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        pintarCeldas(g);
        dibujarRejilla(g);


        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("🖱️ Izq: pintar | 🖱️ Der: iniciar | Rueda: resetear", 10, 20);

        g.dispose();
        bs.show();
    }

    private void manejarClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            marcarCelda(e);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (!hayCeldasVivas()) {
                juego.seedAleatoria();
            }
            simulando = true;
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            simulando = false;
            resetearMapa();
        }
    }


    private void resetearMapa() {
        juego.reset();
    }

    private boolean hayCeldasVivas() {
        for (boolean[] fila : juego.vida) {
            for (boolean celula : fila) {
                if (celula) return true;
            }
        }
        return false;
    }


    private Point marcarCelda(MouseEvent e) {

        Point p = e.getPoint();

        int fila = e.getY() / Config.TAM_CELDA;
        int col = e.getX() / Config.TAM_CELDA;

        juego.seedManual(fila, col);
        render();
        return p;

    }

    private void dibujarRejilla(Graphics g) {
        g.setColor(new Color(0, 0, 0, 50)); // negro translúcido

        for (int i = 0; i < getWidth(); i += Config.TAM_CELDA) {
            g.drawLine(i, 0, i, getHeight());
        }

        for (int j = 0; j < getHeight(); j += Config.TAM_CELDA) {
            g.drawLine(0, j, getWidth(), j);
        }
    }


}
