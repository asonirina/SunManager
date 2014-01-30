package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.AbonementsRequest;
import com.sun.manager.events.NewAbonementAddedEvent;
import com.sun.manager.service.SolariumService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * User: iason
 * Date: 30.01.14
 */
public class AbonementRequestController extends AnchorPane implements Initializable {

    @FXML
    TextField letterField;

    @FXML
    TextField codeField;

    @FXML
    TextField nameField;

    @FXML
    TextField phoneField;

    @FXML
    Button saveButton;

    SolariumService service = new SolariumService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        letterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Arrays.asList(KeyCode.B, KeyCode.C, KeyCode.D, KeyCode.K, KeyCode.M).contains(keyEvent.getCode())) {
                    codeField.setText(service.getCodeBySymbol(keyEvent.getCode().toString()));

                }
            }
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AbonementsRequest request = new AbonementsRequest(letterField.getText(),
                        Long.parseLong(codeField.getText()), nameField.getText(), phoneField.getText());
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
                App.getInstance().getEventBus().post(new NewAbonementAddedEvent(request));

            }
        });

    }
}
