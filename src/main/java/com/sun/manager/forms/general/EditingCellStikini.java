package com.sun.manager.forms.general;

import com.sun.manager.forms.general.EditingCell;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
                    commitEdit((T2)String.format("стикини: %d шт", Integer.parseInt(textField.getText())));
                }
            }
        });
    }

    @Override
    public void updateItem(T2 item, boolean empty) {
        super.updateItem(item, empty);
        if(!((String)item).startsWith("стикини")) {
            setEditable(false);
        }
    }

    @Override
    protected void setOnEditStart() {
       textField.clear();
    }
}
