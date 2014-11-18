package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.dto.AvailableAbonements;
import com.sun.manager.events.AvailableAbonementsCreatedEvent;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class AddAbonementController extends AnchorPane implements Initializable {

    @FXML
    TextField letterField, minutesField, priceField, durationField, timeField;
    @FXML
    CheckBox limitBox;
    @FXML
    Label zeroLabel;

    @FXML
    Button saveButton, cancelButton;

    SolariumService service = new SolariumService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        limitBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(limitBox.isSelected()){
                    timeField.setVisible(true);
                    zeroLabel.setVisible(true);
                } else {
                    timeField.setVisible(false);
                    zeroLabel.setVisible(false);
                }
            }
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                String letter = letterField.getText();
                if (letter.isEmpty()) {
                    new AlertDialog((Stage) cancelButton.getScene().getWindow(), "Введите букву!", 1).showAndWait();
                    return;
                }
                if (validateFields()) {
                    if (service.isAbonementExistsByLetter(letter)) {
                        new AlertDialog((Stage) cancelButton.getScene().getWindow(), "Абонемент с такой буквой уже существует!", 1).showAndWait();
                        return;
                    }
                    Integer time = 24;
                    if (limitBox.isSelected()) {
                        time = Integer.valueOf(timeField.getText());
                    }
                    AvailableAbonements newAbon = new AvailableAbonements(letter, Integer.valueOf(priceField.getText()),
                            Integer.valueOf(minutesField.getText()), Integer.valueOf(durationField.getText()), time);
                    service.createNewAbonement(newAbon);
                    App.getInstance().getEventBus().post(new AvailableAbonementsCreatedEvent(newAbon));
                    ((Stage) cancelButton.getScene().getWindow()).close();
                }
            }
        });

        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    private boolean validateFields() {
        try {
            Integer.parseInt(minutesField.getText());
            Integer.parseInt(priceField.getText());
            Integer.parseInt(durationField.getText());
            if (limitBox.isSelected()) {
                Integer.parseInt(timeField.getText());
            }
        } catch (NumberFormatException ex) {
            new AlertDialog((Stage) cancelButton.getScene().getWindow(), "Введите число!", 1).showAndWait();
            return false;
        }
        return true;
    }


}
