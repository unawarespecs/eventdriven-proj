package io.github.unawarespecs.appfx.service;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;
import java.util.List;


public interface AppService {
    void addShape(Shape shape);
    void updateShape(Shape shape);
    void deleteShape(Shape shape);
    List<Shape> getShapes();

    void  setSelectedShape(Shape shape);
    Shape getSelectedShape();

    ShapeMode getShapeMode();
    void setShapeMode(ShapeMode shapeMode);

    Color getColor();
    void setColor(Color color);

    Color getFillColor();
    void setFillColor(Color color);

    int getLineThickness();
    void setLineThickness(int thickness);

    String getImageFileName();
    void  setImageFileName(String imageFileName);

    String getTextContent();
    void setTextContent(String text);

    Font getTextFont();
    void setTextFont(Font font);

    String getShapeImageFileName();
    void  setShapeImageFileName(String imageFileName);

    String getFileName();
    void  setFileName(String imageFileName);

    void open();
    void close();
    void exit();

    void save();

    void repaint();

    void setTitle(String title);

    void undo();
    void redo();

    void setShapeXLocation(int x);
    void setShapeYLocation(int y);
    void setShapeXEnd(int x);
    void setShapeYEnd(int y);
 }
