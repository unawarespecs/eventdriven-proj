package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.model.Shape;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

public class DeleteShapeCommand implements Command {

    private  AppService appService;
    Shape shape;
    public DeleteShapeCommand(AppService appService, Shape shape){
        this.appService=appService;
        this.shape = shape;
    }
    @Override
    public void execute() {
        appService.deleteShape(shape);
    }

    @Override
    public void undo() {
        appService.addShape(shape);
    }

    @Override
    public void redo() {
        appService.redo();
    }
}
