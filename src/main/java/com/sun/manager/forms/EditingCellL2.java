package com.sun.manager.forms;

import com.sun.manager.forms.alert.AlertDialog;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * User: iason
 * Date: 08.05.14
 */
public class EditingCellL2<T1, T2> extends EditingCell<T1, T2> {
    @Override
    protected void setOnKey() {
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit((T2) String.format("L2= %s", textField.getText()));
                }
            }
        });
    }

    @Override
    protected void setOnEditStart() {
       textField.clear();
    }


    @Override
    public void updateItem(T2 item, boolean empty) {
        super.updateItem(item, empty);
        if(!((String)item).startsWith("L2")) {
            setEditable(false);
        }
    }
}
