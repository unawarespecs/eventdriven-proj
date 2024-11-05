package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.appfx.service.Renderer;

import java.awt.*;

public abstract class BaseRenderer implements Renderer {
    protected int w=5;
    @Override
    public void showHandles(Graphics g, Shape shape) {
        showHandles(g, shape, false);
    }
    @Override
    public void showHandles(Graphics g, Shape shape, boolean xor) {
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
        } else {
            g2.setColor(shape.getColor());
            g2.drawRect(x - w, y - w, 2 * w, 2 * w);
            g2.drawRect(x - w, y + (height / 2) - w, 2 * w, 2 * w);
            g2.drawRect(x - w, y + (height) - w, 2 * w, 2 * w);

            g2.drawRect(x + (width / 2) - w, y - w, 2 * w, 2 * w);
            g2.drawRect(x + (width / 2) - w, y + (height / 2) - w, 2 * w, 2 * w);
            g2.drawRect(x + (width / 2) - w, y + height - w, 2 * w, 2 * w);

            g2.drawRect(x + width - w, y - w, 2 * w, 2 * w);
            g2.drawRect(x + width - w, y + (height / 2) - w, 2 * w, 2 * w);
            g2.drawRect(x + width - w, y + height - w, 2 * w, 2 * w);
        }
    }
    @Override
    public boolean isSelected(Shape shape, int px, int py) {
        Point start = shape.getLocation();
        Point end = shape.getEnd();
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
        if  (
                 (       (px > (x-w))         &&  (px < (x+w))          && (py > (y-w))             && (py < (y+w))             )||
                 (       (px > (x-w))         &&  (px < (x+w))          && (py > (y+(height/2)-w))   && (py < (y+(height/2)+w))  )||
                 (       (px > (x-w))         &&  (px < (x+w))          && (py > (y+height-w))       && (py < (y+height+w))      )||
                 (       (px > (x+(width/2)-w)) && (px< (x+(width/2)+w))&& (py > (y-w))              && (py < (y+w))             )||
                 (       (px > (x+(width/2)-w))&& (px <(x+(width/2)+w))&& (py > (y+(height/2)-w))   && (py < (y+(height/2)+w))  )||
                 (       (px > (x+(width/2)-w))&& (px <(x+(width/2)+w)) && (py > (y+height-w))      && (py < (y+height+w))      )||
                 (       (px > (x+width-w))    && (px < (x+width+w))    && (py > (y-w))             && (py < (y+w))             ) ||
                 (       (px > (x+width-w))    && (px < (x+width+w))    && (py > (y+(height/2)-w))  && (py < (y+(height/2)+w))  )||
                 (       (px > (x+width-w))    && (px < x+width+w)      && (py > (y+height-w))      && (py < (y+height+w)))
            ) {
            shape.setSelected(true);
            return true;
        }
        return false;
    }
}
