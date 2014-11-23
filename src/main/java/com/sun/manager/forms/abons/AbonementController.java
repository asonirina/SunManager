package com.sun.manager.forms.abons;

import com.google.common.eventbus.Subscribe;
import com.sun.manager.App;
import com.sun.manager.dto.AvailableAbonements;
import com.sun.manager.events.AvailableAbonementsCreatedEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class AbonementController extends AnchorPane implements Initializable {

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
    TableColumn<AvailableAbonements, Integer> timeColumn;

    @FXML
    Button addNewButton;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;


    SolariumService service = new SolariumService();

    ObservableList<AvailableAbonements> data = FXCollections.observableArrayList(service.getAvailableAbonements());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.getInstance().getEventBus().register(this);
        abonTable.setEditable(true);
        letterCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, String>("letter"));
        priceCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("price"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("minutes"));
        durationCol.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("duration"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<AvailableAbonements, Integer>("availableTime"));

        priceCol.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());
        minutesCol.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());
        durationCol.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());
        timeColumn.setCellFactory(EventHandlers.cellFactoryAvailableAbonements());

        priceCol.setOnEditCommit(EventHandlers.eventHandlerAbonementsPrice());
        minutesCol.setOnEditCommit(EventHandlers.eventHandlerAbonementsMinutes());
        durationCol.setOnEditCommit(EventHandlers.eventHandlerAbonementsDuration());
        timeColumn.setOnEditCommit(EventHandlers.eventHandlerAbonementsTime());

        abonTable.setItems(data);

        addNewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AddAbonementPage page = new AddAbonementPage();
                try {
                    page.start(new Stage());
                } catch (IOException ex) {
                    new AlertDialog((Stage) cancelButton.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                }
            }
        });

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                service.updateAbonements(data);
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

    @Subscribe
    public void newAbonementAdded(AvailableAbonementsCreatedEvent e) {
        data.add(0, e.getAbon());
        abonTable.setItems(data);
    }

}
