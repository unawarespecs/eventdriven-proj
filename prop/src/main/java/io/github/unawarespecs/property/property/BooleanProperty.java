package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.NullValidator;

public class BooleanProperty extends AbstractProperty<Boolean> {

    public BooleanProperty(String name, Boolean value) {
        super(name, value, new NullValidator());
    }
}
