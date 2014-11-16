package com.sun.manager.forms.admin;

import com.sun.manager.dto.AbonementsData;
import com.sun.manager.dto.Users;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.UsersService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class PhoneBaseController extends VBox implements Initializable {
    static final int countPerPage = 10;
    final UsersService service = new UsersService();
    ObservableList<AbonementsData> data = FXCollections.observableArrayList(service.getPhoneBaseForAllCustomers(0));

    @FXML
    TableView<AbonementsData> phoneTable;

    @FXML
    TableColumn nameColumn;

    @FXML
    TableColumn phoneColumn;

    @FXML
    VBox box;

    Pagination paginator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("clientName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("phone"));

        phoneTable.setItems(data);
        paginator = new Pagination(getCountOfPages(service.getPhoneBaseSize()) ,0);

        paginator.currentPageIndexProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number1) {
                data = FXCollections.observableArrayList(service.getPhoneBaseForAllCustomers(number1.intValue()));
                phoneTable.setItems(data);
            }
        });

        box.getChildren().add(paginator);

    }

    private int getCountOfPages(int baseSize) {
        if(baseSize%countPerPage==0) {
            return baseSize/countPerPage;
        } else return baseSize/countPerPage + 1;

    }

}
