package com.sun.manager.forms.admin;

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

    @FXML
    Label errorLabel;

    SolariumService service = new SolariumService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        errorLabel.setVisible(false);
        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (validate()) {
                    String letter = letterField.getText();
                    String code = codeField.getText();
                    int minutes = Integer.parseInt(minutesField.getText());
                    int duration = Integer.parseInt(durationField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    service.createAbonement(letter, code, minutes, duration, price);
                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
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
            errorLabel.setText("Заполните все поля!!");
            errorLabel.setVisible(true);
            return false;
        }

        if (!Arrays.asList("B", "C", "D", "K", "M").contains(letterField.getText())) {
            errorLabel.setText("Введите одну из следующих букв: B, C, D, K, M");
            errorLabel.setVisible(true);
            return false;
        }

        if (!minutesField.getText().matches("\\d+")) {
            errorLabel.setText("Введите число в поле 'Минуты'!");
            errorLabel.setVisible(true);
            return false;
        }

        if (!priceField.getText().matches("\\d+")) {
            errorLabel.setText("Введите число в поле 'Цена'!");
            errorLabel.setVisible(true);
            return false;
        }

        if (!durationField.getText().matches("\\d+")) {
            errorLabel.setText("Введите количество дней в поле 'Срок действия'!");
            errorLabel.setVisible(true);
            return false;
        }

        errorLabel.setVisible(false);
        return true;
    }
}
