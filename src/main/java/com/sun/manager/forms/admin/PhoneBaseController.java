package com.sun.manager.forms.admin;

import com.sun.manager.dto.AbonementsData;
import com.sun.manager.dto.Users;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class PhoneBaseController extends AnchorPane implements Initializable {

    final UsersService service = new UsersService();
    final ObservableList<AbonementsData> data = FXCollections.observableArrayList(service.getPhoneBaseForAllCustomers(0));

    @FXML
    TableView<AbonementsData> phoneTable;

    @FXML
    TableColumn nameColumn;

    @FXML
    TableColumn phoneColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("clientName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<AbonementsData, String>("phone"));

        phoneTable.setItems(data);

    }

}
