package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Parameters(separators = "=")
public class Program {

    @Parameter(names = {"--white"})
    private static String white;
    @Parameter(names = {"--black"})
    private static String black;

    public static void  main(String[] args) throws IOException {
        if (args.length != 2 || !args[0].startsWith("--white=") || !args[1].startsWith("--black=")) {
            System.err.println("Use README.txt");
        } else {
            Program program = new Program();

            JCommander.newBuilder()
                    .addObject(program)
                    .build()
                    .parse(args);
            run();
        }
    }

    private static void run() throws IOException {
        URL url = Logic.class.getResource("/resources/image.bmp");

        if (url == null) {
            System.err.println("Error: Invalid path!");
        } else {
            BufferedImage image = ImageIO.read(url);

            new Logic(white, black).printImage(image);
        }
    }
}
