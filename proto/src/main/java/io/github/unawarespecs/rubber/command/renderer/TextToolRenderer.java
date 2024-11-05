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
        g.setFont(currentFont);
        g.setFont(g.getFont().deriveFont((float) fontSize));
        g.setColor(color);

        g.drawString(enteredText, start.x, start.y);
    }

}
