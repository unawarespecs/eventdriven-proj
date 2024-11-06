package io.github.unawarespecs.rubber.component;

import io.github.unawarespecs.appfx.enums.ShapeMode;
import io.github.unawarespecs.appfx.service.AppService;
import io.github.unawarespecs.property.PropertyOptions;
import io.github.unawarespecs.property.PropertyPanel;
import io.github.unawarespecs.property.cell.SelectionCellComponent;
import io.github.unawarespecs.property.property.*;
import io.github.unawarespecs.property.property.*;
import io.github.unawarespecs.property.property.selection.Item;
import io.github.unawarespecs.property.property.selection.SelectionProperty;
import io.github.unawarespecs.property.validator.CompoundValidator;
import io.github.unawarespecs.property.validator.StringValidator;
import io.github.unawarespecs.property.validator.doubleNumber.DoubleRangeValidator;
import io.github.unawarespecs.property.validator.doubleNumber.DoubleValidator;
import io.github.unawarespecs.property.validator.doubleNumber.DoubleZeroPolicyValidator;

import io.github.unawarespecs.appfx.model.Shape;

import java.util.ArrayList;
import java.util.Arrays;

public class PropertySheet extends PropertyPanel {

    public PropertySheet(PropertyOptions options){
        super(options);

    }

    public void populateTable(AppService appService) {
        PropertyPanel propertyTable = this;
        propertyTable.addEventListener(new EventListener(appService));
        Shape shape  = appService.getSelectedShape();
        if ( shape == null) {
            propertyTable.clear();
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

            BooleanProperty prop3 = new BooleanProperty("Boolean", true);
            FloatProperty prop4 = new FloatProperty("Float", 1.2f);
            StringProperty prop5 = new StringProperty("String", "Test string");
            StringProperty prop6 = new StringProperty("String 2", "test", new StringValidator(
                    new String[]{"test", "test 2", "foo"}
            ));
            DoubleProperty prop8 = new DoubleProperty("Double", 2.34,
                    new CompoundValidator(
                            new DoubleValidator(),
                            new DoubleRangeValidator(-1.2, 45.33, true, false),
                            new DoubleZeroPolicyValidator(false)
                    )
            );
            ActionProperty prop9 = new ActionProperty("Press me", () -> {
                System.out.println("Pressed");
            });
            propertyTable.addProperty(prop9);
        }
        else {
            propertyTable.clear();
            StringProperty targetProp = new StringProperty("Object Type", "Shape");
            propertyTable.addProperty(targetProp);

            Item RectangleItem = new Item<ShapeMode>(ShapeMode.Rectangle, "Rectangle");
            Item EllipseItem = new Item<ShapeMode>(ShapeMode.Ellipse, "Ellipse");
            Item LineItem =    new Item<ShapeMode>(ShapeMode.Line, "Line");
            SelectionProperty shapeProp = new SelectionProperty<>(
                    "Current Shape",
                    new ArrayList<>(Arrays.asList(
                            RectangleItem,
                            EllipseItem,
                            LineItem
                    ))
            );
            propertyTable.addProperty(shapeProp);

            SelectionCellComponent  selectionComponent =  propertyTable.getSelectionCellComponent();


            String className = shape.getClass().getSimpleName();
            if(className.equals("Rectangle")) {
                selectionComponent.setCellEditorValue(RectangleItem);
            }
            else if(className.equals("Ellipse")) {
                selectionComponent.setCellEditorValue(EllipseItem);
            }
            else if(className.equals("Line")) {
                selectionComponent.setCellEditorValue(LineItem);
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

            BooleanProperty prop3 = new BooleanProperty("Boolean", true);
            FloatProperty prop4 = new FloatProperty("Float", 1.2f);
            StringProperty prop5 = new StringProperty("String", "Test string");
            StringProperty prop6 = new StringProperty("String 2", "test", new StringValidator(
                    new String[]{"test", "test 2", "foo"}
            ));
            DoubleProperty prop8 = new DoubleProperty("Double", 2.34,
                    new CompoundValidator(
                            new DoubleValidator(),
                            new DoubleRangeValidator(-1.2, 45.33, true, false),
                            new DoubleZeroPolicyValidator(false)
                    )
            );
            ActionProperty prop9 = new ActionProperty("Press me", () -> {
                System.out.println("Pressed");
            });
            propertyTable.addProperty(prop9);
        }
    }


}
