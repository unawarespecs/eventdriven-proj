package io.github.unawarespecs.rubber.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GPanel extends JPanel {
    private BufferedImage image;

    public GPanel(String imgFile){
        try {
            image = ImageIO.read(new File("proto\\src\\main\\resources\\" + imgFile + ".png"));

        } catch (IOException ex) {
            // handle exception...
        }
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),  this);
    }
}
