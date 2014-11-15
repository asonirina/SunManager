package com.sun.manager.forms;

import com.sun.manager.constants.DataColumnEnum;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public abstract class EditingCell<T1, T2> extends TableCell<T1, T2> {

    protected TextField textField;
    protected DataColumnEnum solarium;
    protected String textBeforeEdit = "";

    public EditingCell() {
        this.setPrefHeight(27);
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
        textBeforeEdit = textField.getText();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem().toString());
        setGraphic(null);
    }

    @Override
    public void updateItem(T2 item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setPrefHeight(25);
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        setOnKey();
        setOnEditStart();

    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }


    protected abstract void setOnKey();
  //  protected abstract void setOnEditStart();

    protected void setOnEditStart() {

    }
}
