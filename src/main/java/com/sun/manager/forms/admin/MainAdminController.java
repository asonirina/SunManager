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
    //main tables
    @FXML
    TableView<NumericData> tableNumber;

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


    // main columns
    @FXML
    TableColumn colNumber;

    @FXML
    TableColumn colVert;

    @FXML
    TableColumn colGreen;

    @FXML
    TableColumn colBlue;

    @FXML
    TableColumn colCosm;

    @FXML
    TableColumn colAbon;

    //// tables with res data
    @FXML
    TableView<ResData> tableNumRes;

    @FXML
    TableColumn colNumRes;

    @FXML
    TableView<ResData> tableVertRes;

    @FXML
    TableColumn colVertRes;

    @FXML
    TableView<ResData> tableGreenRes;

    @FXML
    TableColumn colGreenRes;

    @FXML
    TableView<ResData> tableBlueRes;

    @FXML
    TableColumn colBlueRes;

    @FXML
    TableView<ResData> tableCosmRes;

    @FXML
    TableColumn colCosmRes;

    @FXML
    TableView<ResData> tableAbonRes;

    @FXML
    TableColumn colAbonRes;

    SolariumService solariumService = new SolariumService();
    final ObservableList<BaseSolariumData> vertData = FXCollections.observableArrayList(
            solariumService.getVertSunData(Date.valueOf("2013-12-10")));

    // new Date(Calendar.getInstance().getTime().getTime()); - current date

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setEditableTables();

            setColumnFactory();
            setCellFactory();
            setStyles();

            setOnEdit();

            setNumbers();
            setNumResData();
            setMainData();
            setSolariumResData();
            setCosmResData();
            setAbonResData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setColumnFactory() {
        colGreen.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colVert.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colBlue.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colCosm.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colAbon.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colNumber.setCellValueFactory(new PropertyValueFactory<NumericData, String>("data"));

        colNumRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colVertRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colGreenRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colBlueRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colCosmRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colAbonRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));


    }

    private void setCellFactory() {

        colVert.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());
        colGreen.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());
        colBlue.setCellFactory(EventHandlers.cellFactoryForBaseSolarium());

        colCosm.setCellFactory(EventHandlers.cellFactoryForCosmAbon());
        colAbon.setCellFactory(EventHandlers.cellFactoryForCosmAbon());

        colNumber.setCellFactory(EventHandlers.cellFactoryNum());

        colNumRes.setCellFactory(EventHandlers.cellFactoryBaseRes());
        colVertRes.setCellFactory(EventHandlers.cellFactoryBaseRes());
        colGreenRes.setCellFactory(EventHandlers.cellFactoryBaseRes());
        colBlueRes.setCellFactory(EventHandlers.cellFactoryBaseRes());
        colCosmRes.setCellFactory(EventHandlers.cellFactoryBaseRes());
        colAbonRes.setCellFactory(EventHandlers.cellFactoryBaseRes());
    }

    private void setStyles() {
        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlue.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableVert.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableCosm.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbon.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableNumber.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

        tableNumRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableVertRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableGreenRes.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlueRes.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableCosmRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbonRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

    }

    private void setOnEdit() {
        colGreen.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        colBlue.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        colVert.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
    }

    private void setNumbers() {
        NumericData[] numericDatas = new NumericData[30];
        for (int i = 0; i < 30; i++) {
            numericDatas[i] = new NumericData(i + 1);
        }
        final ObservableList<NumericData> nums = FXCollections.observableArrayList(
                numericDatas);
        tableNumber.setItems(nums);

    }

    private void setNumResData() {
        final ObservableList<ResData> resDatas2 = FXCollections.observableArrayList(null, null, null);
        tableNumRes.setItems(resDatas2);
    }

    private void setSolariumResData() {
        ResData resData[] = new ResData[3];
        resData[0] = new ResData("Итого мин: ");
        resData[1] = new ResData("Итого руб: ");
        resData[2] = new ResData("L2= ");

        final ObservableList<ResData> data = FXCollections.observableArrayList(resData);
        tableVertRes.setItems(data);
        tableGreenRes.setItems(data);
        tableBlueRes.setItems(data);

    }

    private void setMainData() {
        for (BaseSolariumData dat : vertData) {
            dat.generateRes();
        }
        tableVert.setItems(vertData);
        tableGreen.setItems(vertData);
        tableBlue.setItems(vertData);
        tableCosm.setItems(vertData);
        tableAbon.setItems(vertData);
    }

    private void setEditableTables() {
        tableVert.setEditable(true);
        tableGreen.setEditable(true);
        tableBlue.setEditable(true);
    }

    private void setCosmResData() {
        ResData resData[] = new ResData[3];
        resData[0] = new ResData("стикини:", "шт");
        resData[1] = new ResData("к-ка итого:");
        resData[2] = new ResData("к-ка+стикини:");

        final ObservableList<ResData> data = FXCollections.observableArrayList(resData);
        tableCosmRes.setItems(data);
    }

    private void setAbonResData () {
        final ObservableList<ResData> data = FXCollections.observableArrayList(null, new ResData("итого:"), null);
        tableAbonRes.setItems(data);
    }
}
