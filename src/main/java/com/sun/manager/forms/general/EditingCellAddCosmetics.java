package com.sun.manager.forms.general;

import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.forms.general.EditingCell;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * User: iason
 * Date: 08.05.14
 */
public class EditingCellAddCosmetics<T1, T2> extends EditingCell<T1, T2> {
    @Override
    protected void setOnKey() {
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    try {
                        Long value = Long.valueOf(textField.getText());
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
