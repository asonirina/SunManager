package com.sun.manager.forms.admin;

import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                if (validate()) {
                    String letter = letterField.getText();
                    String code = codeField.getText();
                    int minutes = Integer.parseInt(minutesField.getText());
                    int duration = Integer.parseInt(durationField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    String error = service.createAbonement(letter, code, minutes, duration, price);
                    if (error == null) {
                        Stage stage = (Stage) okButton.getScene().getWindow();
                        stage.close();
                    } else {
                        new AlertDialog((Stage) letterField.getScene().getWindow(), error, 1).showAndWait();
                        ;
                    }
                }
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

    private boolean validate() {
        if (StringUtils.isBlank(letterField.getText()) || StringUtils.isBlank(letterField.getText())
                || StringUtils.isBlank(minutesField.getText()) || StringUtils.isBlank(durationField.getText())
                || StringUtils.isBlank(priceField.getText())) {
            new AlertDialog((Stage) letterField.getScene().getWindow(), "Заполните все поля!", 1).showAndWait();
            return false;
        }

        if (!Arrays.asList("B", "C", "D", "K", "M", "O", "G", "R", "H").contains(letterField.getText())) {
            new AlertDialog((Stage) letterField.getScene().getWindow(), "Введите одну из следующих букв: B, C, D, K, M, O, G, R, H", 1).showAndWait();
            return false;
        }

        if (!minutesField.getText().matches("\\d+")) {
            new AlertDialog((Stage) letterField.getScene().getWindow(), "Введите число в поле 'Минуты'!", 1).showAndWait();
            return false;
        }

        if (!priceField.getText().matches("\\d+")) {
            new AlertDialog((Stage) letterField.getScene().getWindow(), "Введите число в поле 'Цена'!", 1).showAndWait();
            return false;
        }

        if (!durationField.getText().matches("\\d+")) {

            new AlertDialog((Stage) letterField.getScene().getWindow(), "Введите количество дней в поле 'Срок действия'!", 1).showAndWait();
            return false;
        }
        return true;
    }
}
