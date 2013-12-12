package com.sun.manager.forms;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 * User: iason
 */
public  class ButtonCell<T> extends TableCell<T, String> {
    final Button cellButton = new Button();

    public ButtonCell(){
        this.setPrefHeight(27);
        cellButton.setPrefWidth(190);
        cellButton.setPrefHeight(10);
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        cellButton.setText(item);
        if(!empty){
            setGraphic(cellButton);
        }
    }
}

