package com.sun.manager.forms.admin;


import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.forms.EditingCell;
import com.sun.manager.forms.ButtonCell;
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

    SolariumDAO dao = new SolariumDAO();
    List<BaseSolariumData> vertData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
        vertData = dao.getSolariumData(1L, Date.valueOf("2013-12-10"));
        tableVert.setEditable(true);
        tableGreen.setEditable(true);
        tableBlue.setEditable(true);

        setColumnFactory();
        setStyles();

            final ObservableList<BaseSolariumData> data2 = FXCollections.observableArrayList(
                            vertData
            );
                for (BaseSolariumData dat : data2) {
                    dat.generateRes();
                }
        tableVert.setItems(data2);
        tableGreen.setItems(data2);
        tableBlue.setItems(data2);
        tableCosm.setItems(data2);
        tableAbon.setItems(data2);
        }
        catch (Exception ex) {
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
        green.setCellFactory(new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
               return new  EditingCell<BaseSolariumData>();
            }
        });

        cosm.setCellFactory(new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new ButtonCell<BaseSolariumData>();
            }
        });

        abon.setCellFactory(new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new ButtonCell<BaseSolariumData>();
            }
        });

        blue.setCellFactory(new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new  EditingCell<BaseSolariumData>();
            }
        });
        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlue.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableVert.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableCosm.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbon.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        vertSun.setCellFactory(new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {

            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new EditingCell<BaseSolariumData>();
            }
        });


        green.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<BaseSolariumData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<BaseSolariumData, String> t) {
                        BaseSolariumData data =
                                (BaseSolariumData) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        data.setTotalPrice(null);
                        data.setAbonementNumber(null);
                        String input = t.getNewValue();
                        int index = input.indexOf(":");
                        data.setMinutes(Long.valueOf(input.substring(0, index)));
                        if (input.contains("$")) {
                            data.setTotalPrice(Long.valueOf(input.substring(input.indexOf("$") + 1)));
                        } else {
                            data.setAbonementNumber(input.substring(index + 1));
                        }

                        data.generateRes();
                    }
                }
        );

        blue.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<BaseSolariumData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<BaseSolariumData, String> t) {
                        BaseSolariumData data =
                                (BaseSolariumData) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        data.setTotalPrice(null);
                        data.setAbonementNumber(null);
                        String input = t.getNewValue();
                        int index = input.indexOf(":");
                        data.setMinutes(Long.valueOf(input.substring(0, index)));
                        if (input.contains("$")) {
                            data.setTotalPrice(Long.valueOf(input.substring(input.indexOf("$") + 1)));
                        } else {
                            data.setAbonementNumber(input.substring(index + 1));
                        }

                        data.generateRes();
                    }
                }
        );

        vertSun.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<BaseSolariumData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<BaseSolariumData, String> t) {
                        BaseSolariumData data =
                                (BaseSolariumData) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        data.setTotalPrice(null);
                        data.setAbonementNumber(null);
                        String input = t.getNewValue();
                        int index = input.indexOf(":");
                        data.setMinutes(Long.valueOf(input.substring(0, index)));
                        if (input.contains("$")) {
                            data.setTotalPrice(Long.valueOf(input.substring(input.indexOf("$") + 1)));
                        } else {
                            data.setAbonementNumber(input.substring(index + 1));
                        }

                        data.generateRes();
                    }
                }
        );


    }
}
