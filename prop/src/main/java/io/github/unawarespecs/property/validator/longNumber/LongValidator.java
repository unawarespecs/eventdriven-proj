package io.github.unawarespecs.property.validator.longNumber;

import io.github.unawarespecs.property.validator.Validator;

/**
 * Default implementation to validate long objects
 */
public class LongValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return isLong(object);
    }

    protected boolean isLong(Object object) {
        try {
            Long.parseLong((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
