package io.github.unawarespecs.property.property;

import io.github.unawarespecs.property.action.Action;
import io.github.unawarespecs.property.validator.NullValidator;

public class ActionProperty extends AbstractProperty<Action> {

    private String actionName;

    public ActionProperty(String actionName, Action action) {
        super("", action, new NullValidator());

        this.actionName = actionName;
    }
    public String getActionName() {
        return actionName;
    }
}
