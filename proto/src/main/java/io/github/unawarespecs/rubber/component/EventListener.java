package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.commandfx.Command;
import io.github.unawarespecs.commandfx.CommandService;
import io.github.unawarespecs.property.event.PropertyEventAdapter;
import io.github.unawarespecs.property.property.Property;
import io.github.unawarespecs.rubber.command.*;
import io.github.unawarespecs.rubber.command.SetFillColorCommand;

import java.awt.*;

class EventListener extends PropertyEventAdapter {
    private AppService appService;

    public EventListener(AppService appservice) {
        this.appService = appservice;
    }

    @Override
    public void onPropertyUpdated(Property property) {
        if (property.getName().equals("Fill color")) {
            Command command = new SetFillColorCommand(appService, (Color) property.getValue());
            CommandService.ExecuteCommand(command);
        } else if (property.getName().equals("Fore color")) {
            appService.setFillColor((Color) property.getValue());
        } else if (property.getName().equals("X Location")) {
            appService.setShapeXLocation((int)property.getValue());
         } else if (property.getName().equals("Y Location")) {
            appService.setShapeYLocation((int)property.getValue());
        } else if (property.getName().equals("X End")) {
            appService.setShapeXEnd((int)property.getValue());
        } else if (property.getName().equals("Y End")) {
            appService.setShapeYEnd((int)property.getValue());
        } else if (property.getName().equals("Line Thickness")) {
            appService.setLineThickness((int)property.getValue());
        }
    }
}