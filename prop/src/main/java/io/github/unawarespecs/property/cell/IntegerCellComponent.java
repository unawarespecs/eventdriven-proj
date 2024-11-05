package io.github.unawarespecs.property.cell;

import io.github.unawarespecs.property.property.IntegerProperty;
import io.github.unawarespecs.property.util.Keys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class IntegerCellComponent extends AbstractCellComponent {

    private IntegerProperty property;
    private JTextField textField;

    public IntegerCellComponent(IntegerProperty property) {
        this.property = property;
        this.textField = new JTextField();

        textField.addKeyListener(new KeyAdapter());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        textField.setText(property.getValue().toString());

        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return property.getValue();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return new JLabel(property.getValue().toString());
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (Keys.ignoreKey(event.getKeyCode())) {
                return;
            }

            if (property.getValidator().validate(textField.getText())) {
                property.setValue(Integer.parseInt(textField.getText()));
                textField.setBackground(options.getBackgroundColor());
                eventDispatcher.dispatchUpdateEvent(property);
            } else {
                textField.setBackground(options.getInvalidColor());
            }
        }
    }
}
