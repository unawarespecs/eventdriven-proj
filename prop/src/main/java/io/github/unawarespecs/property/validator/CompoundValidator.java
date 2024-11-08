package io.github.unawarespecs.property.validator;

import java.util.Arrays;

/**
 * Enabled the use for multiple validators on a single object
 */
public class CompoundValidator implements Validator {

    protected Validator[] validators;

    public CompoundValidator(Validator... validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(Object object) {
        return Arrays.stream(validators).allMatch(v -> v.validate(object));
    }
}
