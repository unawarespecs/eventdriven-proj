package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.appfx.model.Shape;


public class SetThicknessCommand implements Command {
    AppService appservice;
    int thickness;
    int prevThickness;

    public SetThicknessCommand(AppService appservice, int thickness) {
        this.appservice = appservice;
        this.thickness = thickness;
    }

    @Override
    public void execute() {
        Shape shape = appservice.getSelectedShape();
        if (shape == null) {
            prevThickness = appservice.getLineThickness();
            appservice.setLineThickness(thickness);
        } else {
            prevThickness = shape.getLineThickness();
            shape.setLineThickness(thickness);
        }
    }

    @Override
    public void undo() {
        Shape shape = appservice.getSelectedShape();
        if (shape == null) {
            appservice.setLineThickness(prevThickness);
        } else {
            shape.setLineThickness(prevThickness);
        }
    }

    @Override
    public void redo() {
        Shape shape = appservice.getSelectedShape();
        if (shape == null) {
            appservice.setLineThickness(thickness);
        } else {
            shape.setLineThickness(thickness);
        }
    }
}
