package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;

public interface MovingService {
    public void move(Graphics g, Shape shape,Point start, Point newLoc);
}
