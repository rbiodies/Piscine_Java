package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Logic {
    private final char white;
    private final char black;

    public Logic(char white, char black) {
        this.white = white;
        this.black = black;
    }

    public void printImage(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getRGB(x, y);

                if (pixel == Color.BLACK.getRGB()) {
                    System.out.print(black);
                } else if (pixel == Color.WHITE.getRGB()) {
                    System.out.print(white);
                }
            }
            System.out.println();
        }
    }
}
