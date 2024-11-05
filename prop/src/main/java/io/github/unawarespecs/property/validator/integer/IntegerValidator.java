package io.github.unawarespecs.property.validator.integer;

import io.github.unawarespecs.property.validator.Validator;

/**
 * Default implementation to validate integer objects
 */
public class IntegerValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return isInteger(object);
    }

    protected boolean isInteger(Object object) {
        try {
            Integer.parseInt((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
