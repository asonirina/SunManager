package com.sun.manager.forms;

import com.sun.manager.forms.admin.CosmeticsRequestPage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User: iason
 */
public  class ButtonCell<T> extends TableCell<T, String> {
    final Button cellButton = new Button();

    public ButtonCell(){
        this.setPrefHeight(27);
        cellButton.setPrefWidth(190);
        cellButton.setPrefHeight(10);

        cellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                CosmeticsRequestPage page = new CosmeticsRequestPage();
                page.start(new Stage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
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

