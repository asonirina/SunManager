package com.sun.manager.forms.admin;

import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.service.SolariumService;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/**
 * User: iason
 * Date: 29.01.14
 */
public class CosmeticsRequestController extends AnchorPane implements Initializable {

    @FXML
    ListView<Cosmetics> cosmeticsList;

    @FXML
    TextField countText;

    @FXML
    Button addCosmButton;

    @FXML
    ListView<CosmeticsRequest> resultList;

    @FXML
    Button saveButton;

    SolariumService service = new SolariumService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cosmeticsList.setItems(FXCollections.observableArrayList(service.getAllCosmetics()));

        addCosmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cosmetics c = cosmeticsList.getSelectionModel().getSelectedItem();
                long count = Long.parseLong(countText.getText());
                resultList.getItems().add(0, new CosmeticsRequest(count, c));
            }
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<CosmeticsRequest> list = resultList.getItems();
                HashMap<Cosmetics, Long> map = new HashMap<Cosmetics, Long>();
                for (CosmeticsRequest cr: list) {
                   map.put(cr.getCosmetics(), cr.getCount());
                }

               Map<String, Long> result =  service.saveCosmetics(map);
                if (result.isEmpty()) {
                  Stage stage = (Stage)getScene().getWindow();
                    stage.close();

                }
            }
        });


    }
}
