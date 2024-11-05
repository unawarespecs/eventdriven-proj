package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

public class SetImageFileNameCommand implements Command {

    private AppService appService;
    private String imageFilename;
    private String prevImageFilename;

    public SetImageFileNameCommand(AppService appService, String imageFilename){
        this.appService = appService;
        this.imageFilename = imageFilename;
    }

    @Override
    public void execute() {
        prevImageFilename = appService.getImageFileName();
        appService.setImageFileName(imageFilename);
    }

    @Override
    public void undo() {
        appService.setImageFileName(prevImageFilename);
    }

    @Override
    public void redo() {
        appService.setImageFileName(imageFilename);
    }
}
