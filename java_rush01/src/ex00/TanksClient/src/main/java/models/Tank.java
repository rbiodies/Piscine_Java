package models;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tank {

    private int             posiX = -1, posiY = -1;
    private int             width = 559, height = 473;
    private Image[]         tankImg;
    private BufferedImage   ImageBuff;
    private int             direction = 1;

    public Tank() {
        while (posiX < 70 | posiY < 50 | posiY > height - 43 | posiX > width - 43) {
            posiX = (int) (Math.random() * width);
            posiY = (int) (Math.random() * height);
        }
        loadImage(4);
    }

    public void loadImage(int a) {
        tankImg = new Image[4];
        for(int i = a; i < tankImg.length + a; i++) {
            tankImg[i - a] = new ImageIcon("/Users/rbiodies/projects/piscine_java/java_rush01/src/ex00/TanksClient/src/main/resources/images/player.png").getImage();
        }

        ImageBuff = new BufferedImage(tankImg[direction - 1].getWidth(null), tankImg[direction - 1]
                .getHeight(null), BufferedImage.TYPE_INT_RGB);
        ImageBuff.createGraphics().drawImage(tankImg[direction-1], 0, 0, null);
    }
}
