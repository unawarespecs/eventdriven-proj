package io.github.unawarespecs.rubber.command.renderer;

import io.github.unawarespecs.rubber.model.Text;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.service.BaseRenderer;

import java.awt.*;

public class TextToolRenderer extends BaseRenderer {
    @Override
    public void render(Graphics g, Shape shape) {
        render(g, shape, true);
    }

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Point start = shape.getLocation();

        String enteredText = ((Text) shape).getText();
        java.awt.Font font = ((Text) shape).getFont();
        Font fallback = ((Text) shape).getFallbackFont();

        Font currentFont = (font != null) ? font : fallback;
        java.awt.Color color = shape.getColor();
        int fontSize = ((Text) shape).getFontSize();

        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(currentFont);
        g2.setFont(g.getFont().deriveFont((float) fontSize));

        if (xor) {
            g2.setXORMode(shape.getColor());
        } else {
            g2.setColor(color);
        }

        g2.drawString(enteredText, start.x, start.y);

        if(!xor && shape.isSelected()){
            showHandles(g, shape);
        }
    }

}
