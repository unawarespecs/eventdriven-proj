package io.github.unawarespecs.rubber.command.renderer;
import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.service.BaseRenderer;

import java.awt.*;

public class LineRenderer extends BaseRenderer {
    @Override
    public void render(Graphics g, Shape shape) {
        render(g,shape,true);
    }

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();
        if(start == end) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(shape.getLineThickness()));
        if(xor) {
            g2.setXORMode(shape.getColor());
        }
        else {
            g2.setColor(shape.getColor());
        }
        g2.drawLine(start.x,start.y, end.x,end.y);;
        if(shape.isSelected()){
            showHandles(g, shape);
        }
    }
}
