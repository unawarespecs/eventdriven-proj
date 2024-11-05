package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.StringValidator;
import io.github.unawarespecs.property.validator.Validator;

public class StringProperty extends AbstractProperty<String> {

    public StringProperty(String name, String value) {
        super(name, value, new StringValidator());
    }

    public StringProperty(String name, String value, Validator validator) {
        super(name, value, validator);
    }
}
