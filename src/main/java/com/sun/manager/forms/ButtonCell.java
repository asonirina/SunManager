package com.sun.manager.forms;

import com.sun.manager.App;
import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.dto.AbonementsRequest;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.forms.admin.AbonementsRequestPage;
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
public class ButtonCell<T> extends TableCell<T, String> {
    final Button cellButton = new Button();

    public ButtonCell(final DataColumnEnum e) {
        this.setPrefHeight(27);
        cellButton.setPrefWidth(190);
        cellButton.setPrefHeight(10);

        if (App.getInstance().getUser().getRole().equals("derictor")) {
            cellButton.setDisable(true);
        }
        cellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (e == DataColumnEnum.Cosmetics) {
                        CosmeticsRequestPage page = new CosmeticsRequestPage();
                        page.start(new Stage());
                    } else if (e == DataColumnEnum.Abonements) {
                        AbonementsRequestPage page = new AbonementsRequestPage();
                        page.start(new Stage());
                    }
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
        if(item != null && !item.equals("")) {
            cellButton.setDisable(true);
        }
        if (!empty) {
            setGraphic(cellButton);
        }
    }
}

