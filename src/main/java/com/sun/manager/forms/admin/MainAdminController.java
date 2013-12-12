package com.sun.manager.forms.admin;


import com.sun.manager.dto.VerticalSunData;
import com.sun.manager.forms.EditingCell;
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
import java.util.ResourceBundle;

/**
 * User: iason
 */
public class MainAdminController extends AnchorPane implements Initializable {


    @FXML
    TableView<VerticalSunData> tableVert;

    @FXML
    TableView<VerticalSunData> tableGreen;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableVert.setEditable(true);
        tableGreen.setEditable(true);

        setColumnFactory();
        setStyles();

        VerticalSunData vs = new VerticalSunData();
        vs.setMinutes(5L);
        vs.setAbonementNumber("123");
        vs.generateRes();
        final ObservableList<VerticalSunData> data1 = FXCollections.observableArrayList(
                vs, vs
        );

        tableVert.setItems(data1);
        tableGreen.setItems(data1);

    }

    private void setColumnFactory() {
        green.setCellValueFactory(new PropertyValueFactory<VerticalSunData, String>("res"));
        vertSun.setCellValueFactory(new PropertyValueFactory<VerticalSunData, String>("res"));
    }

    private void setStyles() {
        green.setCellFactory(new Callback<TableColumn<VerticalSunData, String>, TableCell<VerticalSunData, String>>() {

            @Override
            public TableCell<VerticalSunData, String> call(TableColumn<VerticalSunData, String> p) {
               return new  EditingCell<VerticalSunData>();
            }
        });

        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        vertSun.setCellFactory(new Callback<TableColumn<VerticalSunData, String>, TableCell<VerticalSunData, String>>() {

            @Override
            public TableCell<VerticalSunData, String> call(TableColumn<VerticalSunData, String> p) {
                return new EditingCell<VerticalSunData>();
            }
        });


        green.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<VerticalSunData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<VerticalSunData, String> t) {
                        VerticalSunData data =
                                (VerticalSunData) t.getTableView().getItems().get(t.getTablePosition().getRow());
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
                new EventHandler<TableColumn.CellEditEvent<VerticalSunData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<VerticalSunData, String> t) {
                        VerticalSunData data =
                                (VerticalSunData) t.getTableView().getItems().get(t.getTablePosition().getRow());
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
