package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.Validator;
import io.github.unawarespecs.property.validator.doubleNumber.DoubleValidator;

public class DoubleProperty extends AbstractProperty<Double> {

    public DoubleProperty(String name, Double value) {
        super(name, value, new DoubleValidator());
    }

    public DoubleProperty(String name, Double value, Validator validator) {
        super(name, value, validator);
    }
}
