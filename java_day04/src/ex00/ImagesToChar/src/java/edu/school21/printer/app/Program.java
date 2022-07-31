package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {

    public static void  main(String[] args) throws IOException {
        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Use README.txt");
        } else {
            File file = new File(args[2]);

            if (!file.exists() || !file.isFile()) {
                System.err.println("Error: Invalid path!");
            } else {
                char white = args[0].charAt(0);
                char black = args[1].charAt(0);
                BufferedImage image = ImageIO.read(file);

                new Logic(white, black).printImage(image);
            }
        }
    }
}
