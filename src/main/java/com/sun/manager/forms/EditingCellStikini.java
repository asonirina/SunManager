package com.sun.manager.forms;

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
public class EditingCellStikini<T1, T2> extends EditingCell<T1, T2> {
    @Override
    protected void setOnKey() {
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit((T2) textField.getText());
                }
            }
        });
    }
}
