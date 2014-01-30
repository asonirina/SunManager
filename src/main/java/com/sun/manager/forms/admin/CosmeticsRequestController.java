package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.events.NewCosmeticsAddedEvent;
import com.sun.manager.service.SolariumService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

    @FXML
    Button deleteButton;

    @FXML
    TextArea errorArea;

    SolariumService service = new SolariumService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cosmeticsList.setItems(FXCollections.observableArrayList(service.getAllCosmetics()));

        errorArea.setEditable(false);

        addCosmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CosmeticsRequest cr = new CosmeticsRequest(Long.parseLong(countText.getText()),
                        cosmeticsList.getSelectionModel().getSelectedItem());
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
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<CosmeticsRequest> list = resultList.getItems();
                HashMap<Cosmetics, Long> map = new HashMap<Cosmetics, Long>();
                for (CosmeticsRequest cr : list) {
                    map.put(cr.getCosmetics(), cr.getCount());
                }

                Map<String, Long> result = service.saveCosmetics(map);
                if (result.isEmpty()) {
                    Stage stage = (Stage) deleteButton.getScene().getWindow();
                    stage.close();
                    App.getInstance().getEventBus().post(new NewCosmeticsAddedEvent(list));
                } else {
                    StringBuilder sb = new StringBuilder("Sorry, there are no some items in stock: \n");
                    for (Map.Entry<String, Long> entry : result.entrySet()) {
                        sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
                    }

                    errorArea.setText(sb.toString());
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
