package io.github.unawarespecs.property.validator.floatNumber;

import io.github.unawarespecs.property.validator.Validator;

public class FloatZeroPolicyValidator implements Validator {

    protected boolean allowZero;

    public FloatZeroPolicyValidator(boolean allowZero) {
        this.allowZero = allowZero;
    }

    public FloatZeroPolicyValidator() {
        this(true);
    }

    @Override
    public boolean validate(Object object) {
        double value = Float.parseFloat((String) object);

        return value != 0 || allowZero;
    }
}
