package io.github.unawarespecs.property.validator;

/**
 * Accepts all incoming objects
 */
public class NullValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return true;
    }
}
