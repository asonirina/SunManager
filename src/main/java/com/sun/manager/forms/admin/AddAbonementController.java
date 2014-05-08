package com.sun.manager.forms.admin;

import com.sun.manager.dto.AvailableAbonements;
import com.sun.manager.events.EventHandlers;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
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
    TableView<AvailableAbonements> abonTable;

    @FXML
    TableColumn<AvailableAbonements, String> letterCol;

    @FXML
    TableColumn<AvailableAbonements, Integer> priceCol;

    @FXML
    TableColumn<AvailableAbonements, Integer> minutesCol;

    @FXML
    TableColumn<AvailableAbonements, Integer> durationCol;


    @FXML
    Button okButton;

    @FXML
    Button cancelButton;


    SolariumService service = new SolariumService();

    ObservableList<AvailableAbonements> data = FXCollections.observableArrayList(service.getAvailableAbonements());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        abonTable.setEditable(true);
        letterCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, String>("letter"));
        priceCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("price"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("minutes"));
        durationCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("duration"));

        priceCol.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());
        minutesCol.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());
        durationCol.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());

        priceCol.setOnEditCommit(EventHandlers.eventHandlerAbonementsPrice());
        minutesCol.setOnEditCommit(EventHandlers.eventHandlerAbonementsMinutes());
        durationCol.setOnEditCommit(EventHandlers.eventHandlerAbonementsDuration());

        abonTable.setItems(data);

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            //   save
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
