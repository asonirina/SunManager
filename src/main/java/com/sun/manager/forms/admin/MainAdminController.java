package com.sun.manager.forms.admin;


import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.events.EventHandlers;
import com.sun.manager.forms.EditingCell;
import com.sun.manager.forms.ButtonCell;
import com.sun.manager.service.SolariumService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: iason
 */
public class MainAdminController extends AnchorPane implements Initializable {


    @FXML
    TableView<BaseSolariumData> tableVert;

    @FXML
    TableView<BaseSolariumData> tableGreen;

    @FXML
    TableView<BaseSolariumData> tableBlue;

    @FXML
    TableView<BaseSolariumData> tableCosm;

    @FXML
    TableView<BaseSolariumData> tableAbon;

    @FXML
    TableColumn vertSun;

    @FXML
    TableColumn green;

    @FXML
    TableColumn blue;

    @FXML
    TableColumn cosm;

    @FXML
    TableColumn abon;

    SolariumService solariumService = new SolariumService();
    final ObservableList<BaseSolariumData> vertData = FXCollections.observableArrayList(
            solariumService.getVertSunData(Date.valueOf("2013-12-10")));

    // new Date(Calendar.getInstance().getTime().getTime()); - current date

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableVert.setEditable(true);
            tableGreen.setEditable(true);
            tableBlue.setEditable(true);

            setColumnFactory();
            setStyles();

            for (BaseSolariumData dat : vertData) {
                dat.generateRes();
            }
            tableVert.setItems(vertData);
            tableGreen.setItems(vertData);
            tableBlue.setItems(vertData);
            tableCosm.setItems(vertData);
            tableAbon.setItems(vertData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setColumnFactory() {
        green.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        vertSun.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        blue.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        cosm.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        abon.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
    }

    private void setStyles() {
        vertSun.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());
        green.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());
        blue.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());

        cosm.setCellFactory(EventHandlers.cellFactoryForCosmAbon());
        abon.setCellFactory(EventHandlers.cellFactoryForCosmAbon());

        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlue.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableVert.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableCosm.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbon.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());


        green.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        blue.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        vertSun.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());


    }
}
