package com.sun.manager.forms.admin;

import com.sun.manager.service.SolariumService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    TextField letterField;

    @FXML
    TextField codeField;

    @FXML
    TextField minutesField;

    @FXML
    TextField durationField;

    @FXML
    TextField priceField;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    SolariumService service = new SolariumService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String letter = letterField.getText();
                String code = codeField.getText();
                int minutes =  Integer.parseInt(minutesField.getText());
                int duration = Integer.parseInt(durationField.getText());
                int price = Integer.parseInt(priceField.getText());
                service.createAbonement(letter, code, minutes, duration, price);
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });

        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
