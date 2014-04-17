package com.sun.manager.forms;

import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.forms.alert.AlertDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditingCell<T1, T2> extends TableCell<T1, T2> {

    private TextField textField;

    private DataColumnEnum solarium;

    public EditingCell(DataColumnEnum solarium) {
        this.setPrefHeight(27);
        this.solarium = solarium;
    }

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
        if (solarium != null) {
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        String value = textField.getText();
                        if (value.contains("$")) {
                            try {
                                SolariumDAO dao = new SolariumDAO();
                                int minutes = new Scanner(textField.getText()).useDelimiter("\\D+").nextInt();
                                Long sum = minutes * dao.getOneMinutePriceById(solarium.getSolariumNo());
                                value = minutes + " : $ " + sum;
                                textField.setText(value);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                        commitEdit((T2) value);
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    } else if (t.getText().equals("$")) {
                        try {
                            SolariumDAO dao = new SolariumDAO();

                            Long sum = new Scanner(textField.getText()).useDelimiter("\\D+").nextInt() * dao.getOneMinutePriceById(solarium.getSolariumNo());
                            String value = textField.getText() + " " + sum;
                            textField.setText(value);
                            commitEdit((T2) value);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        } else {
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        try {
                            Long value = Long.valueOf(textField.getText());
                            commitEdit((T2) value);
                        } catch (NumberFormatException ex) {
                            new AlertDialog((Stage)textField.getScene().getWindow(), "Введите число!", 1).showAndWait();
                            cancelEdit();
                        }
                    }
                }
            });
        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
