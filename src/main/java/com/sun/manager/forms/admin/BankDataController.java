package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.constants.SunConstants;
import com.sun.manager.dto.AbonementsRequest;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.dto.StatisticData;
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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class BankDataController extends AnchorPane implements Initializable {

    @FXML
    TextField bankMorning;

    @FXML
    TextField residueField;

    @FXML
    TextField bookPerDayField;

    @FXML
    TextField bankField;

    @FXML
    TextField officialSalaryField;

    @FXML
    TextField quenchingField;

    @FXML
    TextField accumulationField;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    Date date = App.getInstance().getUser().getRole().equals("derictor") ? App.getInstance().getSelectedDate() :
            new Date(App.getInstance().getSelectedDate().getTime() - SunConstants.MILLIS_IN_DAY);
    StatisticsService service = new StatisticsService();
    SolariumService solariumService = new SolariumService();

    List<BaseSolariumData> vertData = solariumService.getSunData(date, SolariumEnum.Vertical);
    List<BaseSolariumData> greenData = solariumService.getSunData(date, SolariumEnum.Green);
    List<BaseSolariumData> blueData = solariumService.getSunData(date, SolariumEnum.Blue);
    List<CosmeticsRequest> cosmeticsData = solariumService.getCosmByDate(date);
    List<AbonementsRequest> abonementsData = solariumService.getAbonByDate(date);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final Map<String, Integer> data = service.getQuenchingAndAccumulation(date);
        if (App.getInstance().getUser().getRole().equals("derictor")) {
            residueField.setEditable(true);
            bookPerDayField.setEditable(true);
        }
        final Integer residue = getValue(service.getResidue(date));

        bankMorning.setText(residue.toString());

        quenchingField.setText(String.valueOf(data.get("quenching") + 1));
        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int bookingPerDay = getValue(bookPerDayField.getText());
                int bank = getValue(bankField.getText());
                int quenching = getValue(quenchingField.getText());
                int newAccumulation = data.get("accumulation") + bookingPerDay;
                int ozp = getValue(officialSalaryField.getText());

                int newResidue = residue+bookingPerDay-bank-ozp;

                final StatisticData statisticData = new StatisticData();
                statisticData.setResidue(newResidue);
                statisticData.setBookingPerDay(bookingPerDay);
                statisticData.setBank(bank);
                statisticData.setOfficialSalary(getValue(officialSalaryField.getText()));
                statisticData.setQuenching(quenching);
                statisticData.setStartDate(date);
                statisticData.setAccumulation(newAccumulation);
                service.saveStatisticData(statisticData);
            }
        });
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                ((Stage) cancelButton.getScene().getWindow()).close();
            }
        });
    }

    private Integer getBookPerDay() {
        Long res = 0L;
        for (BaseSolariumData data : vertData) {
            Long price = data.getTotalPrice();
            if (price != null) {
                res += price;
            }
        }

        for (BaseSolariumData data : greenData) {
            Long price = data.getTotalPrice();
            if (price != null) {
                res += price;
            }
        }

        for (BaseSolariumData data : blueData) {
            Long price = data.getTotalPrice();
            if (price != null) {
                res += price;
            }
        }

        for (CosmeticsRequest data : cosmeticsData) {
            Long price = data.getCount() * data.getCosmetics().getPrice();
            if (price != null) {
                res += price;
            }
        }

        for (AbonementsRequest data : abonementsData) {
            Long price = data.getPrice();
            if (price != null) {
                res += price;
            }
        }
        return res.intValue();
    }

    private Integer getValue(Integer value) {
        if (value == null) {
            return 0;

        }

        return value;
    }

    private Integer getValue(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        return Integer.valueOf(value);
    }

}
