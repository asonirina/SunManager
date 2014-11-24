package com.sun.manager.forms.stat;

import com.sun.manager.App;
import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.constants.SunConstants;
import com.sun.manager.dto.AbonementsRequest;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.dto.StatisticData;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import com.sun.manager.service.StatisticsService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class BankPerDayController extends AnchorPane implements Initializable {

    @FXML
    TextField bankField;

    @FXML
    Button saveButton;

    StatisticsService service = new StatisticsService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Integer.parseInt(bankField.getText());
                } catch (NumberFormatException ex) {
                    new AlertDialog((Stage) saveButton.getScene().getWindow(), "Введите число!!", 1).showAndWait();
                    return;
                }
                service.saveBank(Integer.valueOf(bankField.getText()));
                ((Stage) saveButton.getScene().getWindow()).close();
            }
        });

    }

}
