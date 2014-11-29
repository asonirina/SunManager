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
import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
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
public class PeriodDataController extends AnchorPane implements Initializable {
    @FXML
    AnchorPane anchorPane;

    @FXML
    Button showButton;

    @FXML
    Label generalBank, adminBank;

    DatePicker picker1 = new DatePicker();
    DatePicker picker2 = new DatePicker();

    SolariumService service = new SolariumService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generalBank.setFont(new Font("Segoe UI Semibold", 40));
        adminBank.setFont(new Font("Segoe UI Semibold", 40));

        picker1.setLayoutX(100);
        picker1.setLayoutY(64);

        picker1.getStylesheets().add(this.getClass().getResource("datePicker.css").toExternalForm());
        picker1.getCalendarView().setShowWeeks(false);
        picker1.setSelectedDate(new java.util.Date());
        picker1.getCalendarView().todayButtonTextProperty().set("Сегодня");

        anchorPane.getChildren().add(picker1);

        picker2.setLayoutX(314);
        picker2.setLayoutY(64);

        picker2.getStylesheets().add(this.getClass().getResource("datePicker.css").toExternalForm());
        picker2.getCalendarView().setShowWeeks(false);
        picker2.setSelectedDate(new java.util.Date());
        picker2.getCalendarView().todayButtonTextProperty().set("Сегодня");

        anchorPane.getChildren().add(picker2);

        showButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (picker1.getSelectedDate().getTime() > picker2.getSelectedDate().getTime()) {
                    new AlertDialog((Stage) showButton.getScene().getWindow(), "Первая дата должна быть меньше второй!!", 1).showAndWait();
                    return;
                }
                Map<String, Integer> map = service.getMoneyForPeriod(picker1.getSelectedDate(), picker2.getSelectedDate());
                generalBank.setText(String.valueOf(map.get("bank")));
                adminBank.setText(String.valueOf(map.get("bankByAdmin")));
                generalBank.setVisible(true);
                adminBank.setVisible(true);
            }
        });

    }


}
