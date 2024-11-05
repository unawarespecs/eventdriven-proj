package io.github.unawarespecs.rubber.model;

import io.github.unawarespecs.appfx.enums.EditMode;
import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.model.Shape;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class DrawingState {
    private io.github.unawarespecs.appfx.model.Shape selected = null;
    private ShapeMode shapeMode;
    private EditMode editMode;
    private Color color;
    private Color backColor;
    private Color fillColor;
    private String filename;
    private int lineThickness;
    private String imageFileName;
    private String textContent;
    private Font textFont;
    List<Shape> shapes;
    Shape selectedShape;
    public DrawingState(){
        this.shapeMode = ShapeMode.Line;
        this.editMode = EditMode.Create;
        this.color = Color.GREEN;
        this.lineThickness = 1;
        this.textFont = new Font("Arial", Font.PLAIN, 12);
        shapes = new ArrayList<>();
    }
}

