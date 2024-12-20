package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.validator.NullValidator;
import io.github.unawarespecs.property.validator.Validator;

/**
 * Abstract property implementation
 *
 * @param <T>
 */
public abstract class AbstractProperty<T> implements Property<T> {

    protected T value;
    protected String name;
    protected Validator validator;

    public AbstractProperty(String name, T value, Validator validator) {
        this.name = name;
        this.value = value;
        this.validator = validator;
    }

    public AbstractProperty(T value) {
        this("", value, new NullValidator());
    }

    public AbstractProperty(String name) {
        this(name, null, new NullValidator());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    /**
     * Sets the validator for this object
     *
     * @param validator Validator
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
