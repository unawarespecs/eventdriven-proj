package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.Validator;
import io.github.unawarespecs.property.validator.integer.IntegerValidator;

public class IntegerProperty extends AbstractProperty<Integer> {

    public IntegerProperty(String name, Integer value) {
        super(name, value, new IntegerValidator());
    }

    public IntegerProperty(String name, Integer value, Validator validator) {
        super(name, value, validator);
    }
}
