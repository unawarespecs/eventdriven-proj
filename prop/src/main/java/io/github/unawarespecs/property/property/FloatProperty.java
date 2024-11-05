package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.Validator;
import io.github.unawarespecs.property.validator.floatNumber.FloatValidator;

public class FloatProperty extends AbstractProperty<Float> {

    public FloatProperty(String name, Float value) {
        super(name, value, new FloatValidator());
    }

    public FloatProperty(String name, Float value, Validator validator) {
        super(name, value, validator);
    }
}
