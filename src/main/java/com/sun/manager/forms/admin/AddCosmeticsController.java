package com.sun.manager.forms.admin;

import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.service.SolariumService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    ListView<Cosmetics> cosmList;

    @FXML
    ListView<CosmeticsRequest> resultList;

    @FXML
    TextField nameField;

    @FXML
    TextField countField;

    @FXML
    TextField newCountField;

    @FXML
    TextField priceField;

    @FXML
    Button addNewButton;

    @FXML
    Button addButton;
    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    @FXML
    Label errorLabel;

    @FXML
    Label addErrorLabel;

    SolariumService service = new SolariumService();

    ObservableList<Cosmetics> cosmetics = FXCollections.observableArrayList(service.getAllCosmetics());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        errorLabel.setVisible(false);
        cosmList.setItems(cosmetics);

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (countField.getText().matches("\\d+")) {
                    Cosmetics cosm = cosmList.getSelectionModel().getSelectedItem();
                    if (cosm != null) {
                        Long count = Long.parseLong(countField.getText());
                        CosmeticsRequest request = new CosmeticsRequest(count, cosm);
                        resultList.getItems().add(request);
                        errorLabel.setVisible(false);
                    }
                } else {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Введите число!");
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
                    service.createCosmetic(name, (int) price, (int)count);

                    cosmList.setItems(FXCollections.observableArrayList(service.getAllCosmetics()));
                    addErrorLabel.setVisible(false);

                }

            }
        });

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<CosmeticsRequest> items = resultList.getItems();
                HashMap<Cosmetics, Long> map = new HashMap<Cosmetics, Long>();
                for (CosmeticsRequest cr : items) {
                    map.put(cr.getCosmetics(), cr.getCount());
                }
                service.putCosmeticsToStock(map);
                ((Stage) okButton.getScene().getWindow()).close();
            }
        });
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                ((Stage) cancelButton.getScene().getWindow()).close();
            }
        });
    }

    private boolean validate() {
        if (StringUtils.isBlank(nameField.getText()) && StringUtils.isBlank(priceField.getText())) {
            addErrorLabel.setText("Заполните все поля!");
            addErrorLabel.setVisible(true);
            return false;
        }
        if (!priceField.getText().matches("\\d+")) {
            addErrorLabel.setText("Введите число в поле 'Цена'!");
            addErrorLabel.setVisible(true);
            return false;
        }
        if (!newCountField.getText().matches("\\d+")) {
            addErrorLabel.setText("Введите число в поле 'Количество'!");
            addErrorLabel.setVisible(true);
            return false;
        }
        addErrorLabel.setVisible(false);
        return true;
    }
}
