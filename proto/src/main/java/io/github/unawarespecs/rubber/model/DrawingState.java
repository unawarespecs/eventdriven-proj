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
    List<Shape> shapes;
    Shape selectedShape;
    private Shape selected = null;
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
    private Color gradientColorOne;
    private Color gradientColorTwo;

    public DrawingState() {
        this.shapeMode = ShapeMode.Line;
        this.editMode = EditMode.Create;
        this.backColor = Color.WHITE;
        this.color = Color.GREEN;
        this.lineThickness = 1;
        this.textFont = new Font("Arial", Font.PLAIN, 12);
        shapes = new ArrayList<>();
    }
}

