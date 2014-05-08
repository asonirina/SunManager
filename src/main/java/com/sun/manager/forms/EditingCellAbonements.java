package com.sun.manager.forms;

import com.sun.manager.forms.alert.AlertDialog;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * User: iason
 * Date: 08.05.14
 */
public class EditingCellAbonements<T1, T2> extends EditingCell<T1, T2> {
    @Override
    protected void setOnKey() {
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    try {
                        Integer value = Integer.valueOf(textField.getText());
                        commitEdit((T2) value);
                    } catch (NumberFormatException ex) {
                        new AlertDialog((Stage) textField.getScene().getWindow(), "Введите число!", 1).showAndWait();
                        cancelEdit();
                    }
                }
            }
        });
    }
}
