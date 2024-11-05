package io.github.unawarespecs.rubber.service;

import io.github.unawarespecs.appfx.model.Shape;

import java.awt.*;

public interface ScalingService {
    public void scale(Graphics g, Shape shape, Point newLoc);
}
