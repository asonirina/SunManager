package com.sun.manager.forms;

import com.sun.manager.dao.SolariumDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

public class EditingCell<T> extends TableCell<T, String> {

    private TextField textField;

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
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
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
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    String value = textField.getText();
                    if (value != null) {
                        commitEdit(value);
                    } else {
                       commitEdit(null);
                    }
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                } else if (t.getText().equals("$")) {
                    try {
                    SolariumDAO dao = new SolariumDAO();
                    String value = textField.getText() + dao.getOneMinutePriceById(1L);
                    textField.setText(value);
                    commitEdit(value);
                    }catch (SQLException ex){
                       throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
