package edu.school21.printer.logic;

import com.diogonunes.jcolor.Attribute;
import com.diogonunes.jcolor.Ansi;

import java.awt.image.BufferedImage;

public class Logic {

    private final Attribute white;
    private final Attribute black;

    public Logic(String white, String black) {
        this.white = defineColor(white);
        this.black = defineColor(black);
    }

    private Attribute defineColor(String input) {
        switch (input) {
            case "RED":
                return Attribute.RED_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "WHITE":
                return Attribute.WHITE_BACK();
            case "BRIGHT_RED":
                return Attribute.BRIGHT_RED_BACK();
            case "BRIGHT_GREEN":
                return Attribute.BRIGHT_GREEN_BACK();
            case "BRIGHT_BLUE":
                return Attribute.BRIGHT_BLUE_BACK();
            case "BRIGHT_BLACK":
                return Attribute.BRIGHT_BLACK_BACK();
            case "BRIGHT_WHITE":
                return Attribute.BRIGHT_WHITE_BACK();
            default:
                throw new IllegalColorException();
        }
    }

    public void printImage(BufferedImage image) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                if (pixel == -1) {
                    System.out.print(Ansi.colorize(" ", white));
                } else if (pixel == -16777216) {
                    System.out.print(Ansi.colorize(" ", black));
                }
            }
            System.out.println();
        }
    }
}
