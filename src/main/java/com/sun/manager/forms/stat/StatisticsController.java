package com.sun.manager.forms.stat;

import com.google.common.collect.Table;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.CustomerStatistic;
import com.sun.manager.dto.Users;
import com.sun.manager.service.SolariumService;
import com.sun.manager.service.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class StatisticsController extends AnchorPane implements Initializable {

    @FXML
    TableView<CustomerStatistic> statTable;

    @FXML
    TableColumn<CustomerStatistic, String> phoneColumn;

    @FXML
    TableColumn<CustomerStatistic, Long> countColumn;

    SolariumService service = new SolariumService();

    ObservableList<CustomerStatistic> items = FXCollections.observableArrayList(service.getStatistics());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        phoneColumn.setCellValueFactory(new PropertyValueFactory<CustomerStatistic, String>("phone"));
        countColumn.setCellValueFactory(new PropertyValueFactory<CustomerStatistic, Long>("abonementsCount"));

        statTable.setItems(items);
    }


}
