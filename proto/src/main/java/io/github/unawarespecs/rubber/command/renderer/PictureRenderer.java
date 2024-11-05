package io.github.unawarespecs.rubber.command.renderer;

import io.github.unawarespecs.rubber.model.Picture;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.service.BaseRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureRenderer extends BaseRenderer {
    @Override
    public void render(Graphics g, Shape shape) {
        render(g,shape,true);
    }

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();
        if(start == end)
            return;

        int x = start.x;
        int y = start.y;
        int width = end.x-start.x;
        int height = end.y-start.y;
        if(start.x > end.x){
            x = end.x;
            width = start.x-end.x;
        }
        if(start.y>end.y){
            y = end.y;
            height =start.y - end.y;
        }

         String imageFilename = ((Picture) shape).getImageFilename();
        String imgLocation = "./images/"
                + "google48"
                + ".png";
        File input = new File(imageFilename);
        try {
            Image bImage = ImageIO.read(input);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(shape.getLineThickness()));
            if(xor) {
                g2.setXORMode(shape.getColor());
            }
            else {
                g2.setColor(shape.getColor());
            }


            BufferedImage img =  resizeImage((BufferedImage) bImage, width, height);

            if(xor){
                g2.drawRect( x, y,width,height);
            }
            else {
                g2.drawImage(img, x, y, null);
            }
            if(!xor && shape.isSelected()){
                showHandles(g, shape);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }


    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}
