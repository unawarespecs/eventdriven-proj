package io.github.unawarespecs.property.validator.floatNumber;

import io.github.unawarespecs.property.validator.Validator;

/**
 * Default implementation to validate float objects
 */
public class FloatValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return isFloat(object);
    }

    protected boolean isFloat(Object object) {
        try {
            Float.parseFloat((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
