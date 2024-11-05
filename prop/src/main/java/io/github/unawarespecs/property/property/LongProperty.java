package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.Validator;
import io.github.unawarespecs.property.validator.longNumber.LongValidator;

public class LongProperty extends AbstractProperty<Long> {

    public LongProperty(String name, Long value) {
        super(name, value, new LongValidator());
    }

    public LongProperty(String name, Long value, Validator validator) {
        super(name, value, validator);
    }
}
