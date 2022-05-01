package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Logic {

    private final char    white;
    private final char    black;

    public Logic(char white, char black) {
        this.white = white;
        this.black = black;
    }

    public void printImage(BufferedImage image) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                if (pixel == -1) {
                    System.out.print(white);
                } else if (pixel == -16777216) {
                    System.out.print(black);
                }
            }
            System.out.println();
        }
    }
}
