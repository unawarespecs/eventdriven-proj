package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.NullValidator;

import java.awt.*;

public class ColorProperty extends AbstractProperty<Color> {

    public ColorProperty(String name, Color value) {
        super(name, value, new NullValidator());
    }
}
