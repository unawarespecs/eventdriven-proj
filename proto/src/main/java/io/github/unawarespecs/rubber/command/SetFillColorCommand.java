package io.github.unawarespecs.rubber.command;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;

import java.awt.*;



public class SetFillColorCommand implements Command {
    AppService appservice ;
    Color fillColor;
    Color prevColor;
    public SetFillColorCommand(AppService appservice, Color fillColor){
        this.appservice = appservice;
        this.fillColor = fillColor;
    }

    @Override
    public void execute() {
        prevColor = appservice.getFillColor();
        appservice.setFillColor(fillColor);
    }
    @Override
    public void undo() {
        appservice.setFillColor(prevColor);
    }
    @Override
    public void redo() {
        appservice.setFillColor(fillColor);
    }
}


