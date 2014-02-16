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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

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
    TextField priceField;

    @FXML
    Button addNewButton;

    @FXML
    Button addButton;
    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    SolariumService service = new SolariumService();

    ObservableList<Cosmetics> cosmetics = FXCollections.observableArrayList(service.getAllCosmetics());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cosmList.setItems(cosmetics);

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Cosmetics cosm = cosmList.getSelectionModel().getSelectedItem();
                Long count = Long.parseLong(countField.getText());

                CosmeticsRequest request = new CosmeticsRequest(count, cosm);

                resultList.getItems().add(request);
                //save cosm request
            }
        });

        addNewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                String name = nameField.getText();
                long price = Long.parseLong(priceField.getText());
                Cosmetics c = new Cosmetics(null, name, price, 0L);
                service.createCosmetic(name, (int)price, 0);

                cosmList.getItems().add(c);
            }
        });

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<CosmeticsRequest> items = resultList.getItems();
                HashMap<Cosmetics, Long> map = new HashMap<Cosmetics, Long>();
                for (CosmeticsRequest cr: items) {
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
}
