package io.github.unawarespecs.rubber.command;


import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.appfx.model.Shape;

public class AddShapeCommand  implements Command {
    AppService appService;
    Shape shape;
    Shape lastSelectedShape;

    public AddShapeCommand(AppService appService, Shape shape){
        this.appService = appService;
        this.shape = shape;
    }

    @Override
    public void execute() {
        lastSelectedShape = appService.getSelectedShape();
        appService.addShape(shape);
        if(lastSelectedShape != null) {
            lastSelectedShape.setSelected(false);
        }
        appService.setSelectedShape(shape);
    }

    @Override
    public void undo() {
        appService.getShapes().remove(shape);
        shape.setSelected(false);
        lastSelectedShape.setSelected(true);
        appService.setSelectedShape(lastSelectedShape);
    }

    @Override
    public void redo() {
        lastSelectedShape = appService.getSelectedShape();
        appService.getShapes().add(shape);
        lastSelectedShape.setSelected(false);
        shape.setSelected(true);
        appService.setSelectedShape(shape);
        appService.repaint();
    }
}
