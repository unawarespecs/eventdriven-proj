package io.github.unawarespecs.rubber.model;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.rubber.command.renderer.PictureRenderer;
import lombok.Data;

import java.awt.*;

@Data
public class Picture extends Shape {
    String imageFilename;
    public Picture(Point start, Point end, Color color, String imageFilename, int lineThickness){
        super(start, end, color, null, lineThickness);
        this.imageFilename = imageFilename;
        setRenderer(new PictureRenderer());
    }
}
