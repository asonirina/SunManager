package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.AbonementsRequest;
import com.sun.manager.events.NewAbonementAddedEvent;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

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


    @FXML
    Label priceLabel;

    SolariumService service = new SolariumService();
    Map<String, Long> abonInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        letterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (Arrays.asList(KeyCode.B, KeyCode.C, KeyCode.D, KeyCode.K, KeyCode.M).contains(keyEvent.getCode())) {
                    abonInfo = service.getCodeAndPriceBySymbol(keyEvent.getCode().toString());
                    codeField.setText(String.valueOf(abonInfo.get("code")));
                    if (letterField.getText().startsWith("S") && abonInfo.get("price") != null) {
                        priceLabel.setText("Цена: " + (long) (0.45 * abonInfo.get("price")));
                    }

                }
            }
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (validate()) {
                    AbonementsRequest request = new AbonementsRequest(letterField.getText(),
                            Long.parseLong(codeField.getText()), nameField.getText(), phoneField.getText(), Math.round(0.45 * abonInfo.get("price")));
                    request.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
                    service.saveAbonement(Arrays.asList(request));
                    Stage stage = (Stage) saveButton.getScene().getWindow();
                    stage.close();
                    App.getInstance().getEventBus().post(new NewAbonementAddedEvent(request));
                }
            }
        });

    }

    private boolean validate() {
        if (StringUtils.isBlank(nameField.getText()) || StringUtils.isBlank(phoneField.getText())
                || StringUtils.isBlank(letterField.getText()) || StringUtils.isBlank(codeField.getText())) {
            new AlertDialog((Stage) nameField.getScene().getWindow(), "Заполните все поля!", 1).showAndWait();
            return false;
        }

        if (!Arrays.asList("B", "C", "D", "K", "M", "SB", "SC", "SD", "SK", "SM").contains(letterField.getText())) {
            new AlertDialog((Stage) nameField.getScene().getWindow(), "Введите одну из следующих букв: B, C, D, K, M", 1).showAndWait();
            return false;
        }

        if (!phoneField.getText().matches("8[-\\t]?0[\\d]{2}[-\\t]?[\\d]{7}")) {
            new AlertDialog((Stage) nameField.getScene().getWindow(), "Введите номер телефона в формате 8-0xx-xxxxxxx", 1).showAndWait();
            return false;
        }

        if(abonInfo.get("price")==null) {
            new AlertDialog((Stage) nameField.getScene().getWindow(), "Введите другую букву для абонемента!", 1).showAndWait();
            return false;
        }
        return true;
    }
}
