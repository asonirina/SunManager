package com.sun.manager.forms.admin;

import com.sun.manager.dto.AbonementsData;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import com.sun.manager.service.UsersService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class MinutesByPhoneAbonController extends VBox implements Initializable {
    final SolariumService service = new SolariumService();

    @FXML
    TextField abonNumberField;

    @FXML
    TextField phoneField;

    @FXML
    Button findButton;

    @FXML
    Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        resultLabel.setFont(new Font("Arial", 40));

        findButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String abonement = abonNumberField.getText();
                String phoneNumber = phoneField.getText();
                if (abonement.isEmpty() || phoneNumber.isEmpty()) {
                    new AlertDialog((Stage) phoneField.getScene().getWindow(), "Введите данные!", 1).showAndWait();
                    return;
                }

                Integer minutes = service.getResidueMinutesFromAbonement(abonement, phoneNumber);
                if (minutes != null) {
                    resultLabel.setText(String.format("Остаток: %d минут(ы)", minutes));
                    resultLabel.setVisible(true);
                } else {
                    resultLabel.setText("Ничего не найдено");
                    resultLabel.setVisible(true);
                }

            }
        });


    }


}
