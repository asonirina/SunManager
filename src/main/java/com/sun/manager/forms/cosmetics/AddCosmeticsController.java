package com.sun.manager.forms.cosmetics;

import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.CosmeticsRequest;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class AddCosmeticsController extends AnchorPane implements Initializable {
    @FXML
    TableView<Cosmetics> cosmTable;

    @FXML
    TableColumn nameColumn;

    @FXML
    TableColumn countColumn;

    @FXML
    TextField nameField;

    @FXML
    TextField newCountField;

    @FXML
    TextField priceField;

    @FXML
    Button addNewButton;

    @FXML
    Button deleteButton;

    SolariumService service = new SolariumService();

    ObservableList<Cosmetics> cosmetics = FXCollections.observableArrayList(service.getAllCosmetics());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Cosmetics, String>("name"));
        countColumn.setCellValueFactory(new PropertyValueFactory<Cosmetics, Long>("count"));
        countColumn.setCellFactory(EventHandlers.cellFactoryForEditCosmetics());
//        cosmTable.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

        countColumn.setOnEditCommit(EventHandlers.eventHandlerCosmeticsCommit(service));
        cosmTable.setItems(cosmetics);

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cosmetics cosm = cosmTable.getSelectionModel().getSelectedItem();
                if (cosm != null) {
                    cosmTable.getItems().remove(cosm);
                    service.deleteCosmetics(cosm);

                }
            }
        });

        addNewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (validate()) {
                    String name = nameField.getText();
                    long price = Long.parseLong(priceField.getText());
                    long count = Long.parseLong(newCountField.getText());
                    Cosmetics c = new Cosmetics(null, name, price, count);
                    service.createCosmetic(name, (int) price, (int) count);
                    cosmTable.setItems(FXCollections.observableArrayList(service.getAllCosmetics()));
                }
            }
        });
    }

    private boolean validate() {
        if (StringUtils.isBlank(nameField.getText()) && StringUtils.isBlank(priceField.getText())) {

            new AlertDialog((Stage) nameField.getScene().getWindow(), "Заполните все поля!", 1).showAndWait();
            return false;
        }
        if (!priceField.getText().matches("\\d+")) {
            new AlertDialog((Stage) nameField.getScene().getWindow(), "Введите число в поле 'Цена'!", 1).showAndWait();
            return false;
        }
        if (!newCountField.getText().matches("\\d+")) {
            new AlertDialog((Stage) nameField.getScene().getWindow(), "Введите число в поле 'Количество'!", 1).showAndWait();
            return false;
        }
        return true;
    }
}
