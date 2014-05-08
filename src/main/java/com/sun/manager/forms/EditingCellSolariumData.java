package com.sun.manager.forms;

import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.forms.alert.AlertDialog;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * User: iason
 * Date: 08.05.14
 */
public class EditingCellSolariumData<T1, T2> extends EditingCell<T1, T2> {
    public EditingCellSolariumData(DataColumnEnum solarium) {
        this.solarium = solarium;
    }
    @Override
    protected void setOnKey() {
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    String value = textField.getText();
                    if (value.replaceAll(" ", "").matches("[\\d]+:S?[BCDKM]{1}(\\d)+")) {
                        textBeforeEdit = value;
                        commitEdit((T2) value);
                    } else if (value.replaceAll(" ", "").matches("[\\d]+:S?[ORGH]{1}(\\d)+")) {
                        DateFormat dateFormat = new SimpleDateFormat("HH");
                        Calendar cal = Calendar.getInstance();
                        String hours = dateFormat.format(cal.getTime());

                        if (Integer.parseInt(hours) >= 13) {
                            new AlertDialog((Stage) textField.getScene().getWindow(), "Этот абонемент действителен до 13:00", 1).showAndWait();
                            textField.setText(textBeforeEdit);
                            return;
                        } else {
                            textBeforeEdit = value;
                            commitEdit((T2) value);
                        }
                    } else {
                        new AlertDialog((Stage) textField.getScene().getWindow(), "Заполните ячейку в формате: \n" +
                                "Количество: ($)? Номер абонемента", 1).showAndWait();
                        cancelEdit();
                        textField.setText(textBeforeEdit);
                    }
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                } else if (t.getText().equals("$")) {
                    try {
                        SolariumDAO dao = new SolariumDAO();
                        Long sum = new Scanner(textField.getText()).useDelimiter("\\D+").nextInt() * dao.getOneMinutePriceById(solarium.getSolariumNo());
                        String value = textField.getText() + " " + sum;
                        if (textField.getText().replace(" ", "").matches("[\\d]+:\\$")) {
                            textField.setText(value);
                            commitEdit((T2) value);
                            textBeforeEdit = value;
                        } else {
                            new AlertDialog((Stage) textField.getScene().getWindow(), "Заполните ячейку в формате: \n" +
                                    "Количество: ($)? Номер абонемента", 1).showAndWait();
                            cancelEdit();
                            textField.setText(textBeforeEdit);

                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
