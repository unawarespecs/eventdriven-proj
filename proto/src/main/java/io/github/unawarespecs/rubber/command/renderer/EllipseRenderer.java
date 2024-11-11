package io.github.unawarespecs.rubber.command.renderer;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.service.BaseRenderer;

import java.awt.*;

public class EllipseRenderer extends BaseRenderer {
    int w = 5;

    @Override
    public void render(Graphics g, Shape shape) {
        render(g, shape, true);
    }

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();
        if (start == end) {
            return;
        }
        int x = start.x;
        int y = start.y;
        int width = end.x - start.x;
        int height = end.y - start.y;
        if (start.x > end.x) {
            x = end.x;
            width = start.x - end.x;
        }
        if (start.y > end.y) {
            y = end.y;
            height = start.y - end.y;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(shape.getLineThickness()));
        if (xor) {
            g2.setXORMode(shape.getColor());
            g2.drawOval(x, y, width, height);
        } else {
            g2.setColor(shape.getColor());
            if (shape.getFill() != null) {
                g2.setColor(shape.getFill());
                //g2.setColor(new Color(255,255,0,128));
                g2.fillOval(x, y, width, height);
            }
            g2.setColor(shape.getColor());
            g2.drawOval(x, y, width, height);
        }
        if (!xor && shape.isSelected()) {
            showHandles(g, shape);
        }
    }
}
