package com.sun.manager.forms.admin;

import com.sun.manager.dto.AbonementsData;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.UsersService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class AbonsByPhoneController extends VBox implements Initializable {
    static final int countPerPage = 10;
    final UsersService service = new UsersService();
    ObservableList<AbonementsData> data = FXCollections.observableArrayList();

    @FXML
    TextField phoneField;

    @FXML
    TableView<AbonementsData> abonTable;

    @FXML
    TableColumn nameColumn;

    @FXML
    TableColumn letterColumn;

    @FXML
    TableColumn codeColumn;

    @FXML
    TableColumn dateColumn;

    @FXML
    TableColumn minutesColumn;

    @FXML
    VBox box;

    @FXML
    Button findButton;

    Pagination paginator;

    String phoneNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("clientName"));
        letterColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("letter"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("code"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("buyDate"));
        minutesColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("minutes"));

        abonTable.setItems(data);

        paginator = new Pagination(1, 0);

        findButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                phoneNumber = phoneField.getText();
                if(phoneNumber.isEmpty()) {
                    new AlertDialog((Stage) phoneField.getScene().getWindow(), "Неверный номер телефона", 1).showAndWait();
                    return;
                }

                data =  FXCollections.observableArrayList(service.getAvailableAbonementsByPhoneNumber(phoneNumber, 0));
                abonTable.setItems(data);
                paginator.setPageCount(getCountOfPages(service.getAbonsByPhoneSize(phoneNumber)));
                paginator.setCurrentPageIndex(0);

            }
        });

        paginator.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number1) {
                data = FXCollections.observableArrayList(service.getAvailableAbonementsByPhoneNumber(phoneNumber, number1.intValue()));
            }
        });

        box.getChildren().add(paginator);


    }

    private int getCountOfPages(int baseSize) {
        if (baseSize % countPerPage == 0) {
            return baseSize / countPerPage;
        } else return baseSize / countPerPage + 1;

    }

}
