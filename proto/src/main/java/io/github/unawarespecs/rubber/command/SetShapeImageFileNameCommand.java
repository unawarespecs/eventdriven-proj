package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.rubber.model.Picture;

public class SetShapeImageFileNameCommand implements Command {
    Picture  picture;
    private String imageFilename;
    private String prevImageFilename;

    public SetShapeImageFileNameCommand(AppService appService, String imageFilename){
        this.picture =(Picture) appService.getSelectedShape();
        this.prevImageFilename = picture.getImageFilename();
        this.imageFilename = imageFilename;
    }

    @Override
    public void execute() {
        picture.setImageFilename(imageFilename);
    }

    @Override
    public void undo() {
        picture.setImageFilename(prevImageFilename);
    }

    @Override
    public void redo() {
        picture.setImageFilename(imageFilename);
    }
}
