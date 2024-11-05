package io.github.unawarespecs.appfx.service;

import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;

public interface Renderer {
    void render(Graphics g, Shape shape);
    void render(Graphics g, Shape shape, boolean xor);
    void showHandles(Graphics g, Shape shape);
    void showHandles(Graphics g, Shape shape, boolean xor);
    boolean isSelected(Shape shape,int x, int y) ;
}
