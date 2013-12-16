package com.sun.manager.forms.admin;


import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.NumericData;
import com.sun.manager.dto.ResData;
import com.sun.manager.events.EventHandlers;
import com.sun.manager.forms.EditingCell;
import com.sun.manager.forms.ButtonCell;
import com.sun.manager.service.SolariumService;
import javafx.beans.property.SimpleIntegerProperty;
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
    TableView<NumericData> tableNumber;

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

    @FXML
    TableColumn numData;

    @FXML
    TableView<ResData> vertRes;

    @FXML
    TableColumn colNumRes;

    @FXML
    TableView<ResData> numResTable;

    @FXML
    TableColumn colVert;

    SolariumService solariumService = new SolariumService();
    final ObservableList<BaseSolariumData> vertData = FXCollections.observableArrayList(
            solariumService.getVertSunData(Date.valueOf("2013-12-10")));


    // new Date(Calendar.getInstance().getTime().getTime()); - current date

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            NumericData[] numericDatas = new NumericData[30];
            for (int i = 0; i < 30; i++) {
                numericDatas[i] = new NumericData(i + 1);
            }
            final ObservableList<NumericData> nums = FXCollections.observableArrayList(
                    numericDatas);

            ResData resDatas[] = new ResData[3];
            resDatas[0] = new ResData(1000L, "Итого мин: ");
            resDatas[1] = new ResData(200L, "Итого руб: ");
            resDatas[2] = new ResData(2300L, "L2= ");

            final ObservableList<ResData> resDatas1 = FXCollections.observableArrayList(resDatas);

            final ObservableList<ResData> resDatas2 = FXCollections.observableArrayList(null, null, null);
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
            tableNumber.setItems(nums);

            vertRes.setItems(resDatas1);

            numResTable.setItems(resDatas2);

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
        numData.setCellValueFactory(new PropertyValueFactory<NumericData, String>("data"));

        colVert.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));

        colNumRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
    }

    private void setStyles() {
        vertSun.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());
        green.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());
        blue.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());

        cosm.setCellFactory(EventHandlers.cellFactoryForCosmAbon());
        abon.setCellFactory(EventHandlers.cellFactoryForCosmAbon());

        numData.setCellFactory(EventHandlers.cellFactoryNum());

        colVert.setCellFactory(EventHandlers.cellFactoryBaseRes());
        colNumRes.setCellFactory(EventHandlers.cellFactoryBaseRes());

        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlue.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableVert.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableCosm.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbon.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableNumber.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

        vertRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        numResTable.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

        green.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        blue.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        vertSun.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());


    }
}
