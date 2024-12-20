package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.property.PropertyOptions;
import io.github.unawarespecs.property.PropertyPanel;
import io.github.unawarespecs.property.cell.SelectionCellComponent;
import io.github.unawarespecs.property.property.*;
import io.github.unawarespecs.property.property.selection.Item;
import io.github.unawarespecs.property.property.selection.SelectionProperty;

import io.github.unawarespecs.appfx.model.Shape;

import java.util.ArrayList;
import java.util.Arrays;

public class PropertySheet extends PropertyPanel {

    public PropertySheet(PropertyOptions options) {
        super(options);

    }

    public void populateTable(AppService appService) {
        PropertyPanel propertyTable = this;
        propertyTable.addEventListener(new EventListener(appService));
        Shape shape = appService.getSelectedShape();
        propertyTable.clear();
        if (shape == null) {
            String objectType = "Application";
            StringProperty targetProp = new StringProperty("Object Type", objectType);

            propertyTable.addProperty(targetProp);

            SelectionProperty shapeProp = new SelectionProperty<>(
                    "Current Shape",
                    new ArrayList<>(Arrays.asList(
                            new Item<>(ShapeMode.Rectangle, "Rectangle"),
                            new Item<>(ShapeMode.Ellipse, "Ellipse"),
                            new Item<>(ShapeMode.Line, "Line"),
                            new Item<>(ShapeMode.Image, "Picture"),
                            new Item<>(ShapeMode.Text, "Entered Text")
                    ))
            );
            shapeProp.setValue(shape);
            propertyTable.addProperty(shapeProp);
            IntegerProperty xloc = new IntegerProperty("X Location", 12);
            propertyTable.addProperty(xloc);
            IntegerProperty yloc = new IntegerProperty("Y Location", 10);
            propertyTable.addProperty(yloc);
            IntegerProperty xend = new IntegerProperty("X End", 120);
            propertyTable.addProperty(xend);
            IntegerProperty yend = new IntegerProperty("Y End", 100);
            propertyTable.addProperty(yend);
            ColorProperty currentColorProp = new ColorProperty("Fore color", appService.getColor());
            propertyTable.addProperty(currentColorProp);
            ColorProperty currentFillProp = new ColorProperty("Fill color", appService.getFillColor());
            propertyTable.addProperty(currentFillProp);
            IntegerProperty lineThicknessProp = new IntegerProperty("Line Thickness", appService.getLineThickness());
            propertyTable.addProperty(lineThicknessProp);

            ActionProperty prop9 = new ActionProperty("Press me", () -> {
                System.out.println("Pressed");
            });
            propertyTable.addProperty(prop9);
        } else {
            StringProperty targetProp = new StringProperty("Object Type", "Shape");
            propertyTable.addProperty(targetProp);

            Item<ShapeMode> RectangleItem = new Item<>(ShapeMode.Rectangle, "Rectangle");
            Item<ShapeMode> EllipseItem = new Item<>(ShapeMode.Ellipse, "Ellipse");
            Item<ShapeMode> LineItem = new Item<>(ShapeMode.Line, "Line");
            Item<ShapeMode> ImageItem = new Item<>(ShapeMode.Image, "Image");
            Item<ShapeMode> TextItem = new Item<>(ShapeMode.Text, "Text");
            SelectionProperty<ShapeMode> shapeProp = new SelectionProperty<>(
                    "Current Shape",
                    new ArrayList<>(Arrays.asList(
                            RectangleItem,
                            EllipseItem,
                            LineItem,
                            ImageItem,
                            TextItem
                    ))
            );
            propertyTable.addProperty(shapeProp);

            SelectionCellComponent selectionComponent = propertyTable.getSelectionCellComponent();

            String className = shape.getClass().getSimpleName();
            switch (className) {
                case "Rectangle" -> selectionComponent.setCellEditorValue(RectangleItem);
                case "Ellipse" -> selectionComponent.setCellEditorValue(EllipseItem);
                case "Line" -> selectionComponent.setCellEditorValue(LineItem);
                case "Image" -> selectionComponent.setCellEditorValue(ImageItem);
                case "Text" -> selectionComponent.setCellEditorValue(TextItem);
            }

            IntegerProperty xloc = new IntegerProperty("X Location ", shape.getLocation().x);
            propertyTable.addProperty(xloc);
            IntegerProperty yloc = new IntegerProperty("Y Location ", shape.getLocation().y);
            propertyTable.addProperty(yloc);
            IntegerProperty xend = new IntegerProperty("X End", shape.getEnd().x);
            propertyTable.addProperty(xend);
            IntegerProperty yend = new IntegerProperty("Y End", shape.getEnd().y);
            propertyTable.addProperty(yend);
            ColorProperty currentColorProp = new ColorProperty("Fore color", shape.getColor());
            propertyTable.addProperty(currentColorProp);
            ColorProperty currentFillProp = new ColorProperty("Fill color", shape.getFill());
            propertyTable.addProperty(currentFillProp);
            IntegerProperty lineThicknessProp = new IntegerProperty("Line Thickness", shape.getLineThickness());
            propertyTable.addProperty(lineThicknessProp);

            ActionProperty prop9 = new ActionProperty("Press me", () -> {
                System.out.println("Pressed");
            });
            propertyTable.addProperty(prop9);
        }
    }


}
