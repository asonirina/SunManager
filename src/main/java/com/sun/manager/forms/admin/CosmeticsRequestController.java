package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.events.NewCosmeticsAddedEvent;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    Button deleteButton;

    SolariumService service = new SolariumService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cosmeticsList.setItems(FXCollections.observableArrayList(service.getAllCosmetics()));

        addCosmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (countText.getText().matches("\\d+")) {
                    Cosmetics cosmetics = cosmeticsList.getSelectionModel().getSelectedItem();
                    if (cosmetics != null) {
                        CosmeticsRequest cr =
                                new CosmeticsRequest(Long.parseLong(countText.getText()), cosmetics);
                        int index = resultList.getItems().indexOf(cr);
                        if (index != -1) {
                            CosmeticsRequest c = resultList.getItems().get(index);
                            cr.setCount(c.getCount() + cr.getCount());
                            resultList.getItems().remove(index);
                            resultList.getItems().add(index, cr);
                        } else {
                            resultList.getItems().add(0, cr);
                        }
                    }
                } else {
                    new AlertDialog((Stage)countText.getScene().getWindow(),"Введите число", 1).showAndWait();
                }

            }
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<CosmeticsRequest> list = resultList.getItems();
                HashMap<Cosmetics, Long> map = new HashMap<Cosmetics, Long>();
                for (CosmeticsRequest cr : list) {
                    map.put(cr.getCosmetics(), cr.getCount());
                }

                List<CosmeticsRequest> result = service.getCosmeticsFromStock(map);
                if (result.isEmpty()) {
                    Stage stage = (Stage) deleteButton.getScene().getWindow();
                    stage.close();
                    App.getInstance().getEventBus().post(new NewCosmeticsAddedEvent(list));
                } else {
                    StringBuilder sb = new StringBuilder("Следующих товаров нет на складе!: \n");
                    for (CosmeticsRequest cosmeticsRequest: result) {
                        sb.append(cosmeticsRequest.getCosmetics().getName() + " : " + cosmeticsRequest.getCount() + "\n");
                        resultList.getItems().remove(cosmeticsRequest);
                    }
                    new AlertDialog((Stage)saveButton.getScene().getWindow(), sb.toString(), 1).showAndWait();
                }
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resultList.getItems().remove(resultList.getSelectionModel().getSelectedItem());
            }
        });

    }
}
