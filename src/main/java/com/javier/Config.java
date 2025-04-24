package com.javier;

import java.awt.*;

public class Config {
    static int columnasDeseadas = 100;
    static int filasDeseadas = 60;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int screenWidth = screenSize.width;   // ej: 1920
    static int screenHeight = screenSize.height; // ej: 1080

    static int tamCeldaAncho = screenWidth / columnasDeseadas;
    static int tamCeldaAlto = screenHeight / filasDeseadas;

    static int TAM_CELDA = Math.min(tamCeldaAncho, tamCeldaAlto); // Para que quepa en ambos ejes
    

}
