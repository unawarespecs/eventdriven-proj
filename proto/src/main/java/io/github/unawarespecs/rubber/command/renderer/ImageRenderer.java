package io.github.unawarespecs.rubber.command.renderer;

import io.github.unawarespecs.rubber.model.Picture;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.service.BaseRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class ImageRenderer extends BaseRenderer {
    @Override
    public void render(Graphics g, Shape shape) {
        render(g,shape,true);
    }

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        xor = false;
        Point start = shape.getLocation();
        Point end = shape.getEnd();
        String imageFilename = ((Picture) shape).getImageFilename();
        if(start == end)
            return;
        String imgLocation = "./images/"
                + "google48"
                + ".png";
        File input = new File(imageFilename);
        try {
            Image img = ImageIO.read(input);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(shape.getLineThickness()));
            if(xor) {
                g2.setXORMode(shape.getColor());
            }
            else {
                g2.setColor(shape.getColor());
            }
            g2.drawImage(img, end.x, end.y, null);

            if(shape.isSelected()){
                showHandles(g, shape);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }


    }
}
