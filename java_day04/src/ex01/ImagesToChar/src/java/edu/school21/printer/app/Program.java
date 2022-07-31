package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Program {

    public static void  main(String[] args) throws IOException {
        if (args.length != 2 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Use README.txt");
        } else {
            URL url = Logic.class.getResource("/resources/image.bmp");

            if (url == null) {
                System.err.println("Error: Invalid path!");
            } else {
                char white = args[0].charAt(0);
                char black = args[1].charAt(0);
                BufferedImage image = ImageIO.read(url);

                new Logic(white, black).printImage(image);
            }
        }
    }
}
