package com.sun.manager.forms.admin;

import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.CosmeticsRequest;
import com.sun.manager.dto.Users;
import com.sun.manager.service.SolariumService;
import com.sun.manager.service.UsersService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import javax.management.relation.Role;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 13.02.14
 */
public class UsersController extends AnchorPane implements Initializable {

    @FXML
    ListView<Users> usersList;

    @FXML
    TextField nameField;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField repeatField;

    @FXML
    Button addButton;

    @FXML
    Button deleteButton;

    @FXML
    Label errorLabel;

    UsersService service = new UsersService();

    ObservableList<Users> users = FXCollections.observableArrayList(service.getUsersByRole("admin"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        errorLabel.setVisible(false);
        usersList.setItems(users);

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (validate()) {
                    Users user = new Users();
                    user.setName(nameField.getText());
                    user.setLogin(loginField.getText());
                    user.setPassword(passwordField.getText());
                    user.setRole("admin");
                    service.addUser(user);

                    usersList.getItems().add(user);
                }
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Users user = usersList.getSelectionModel().getSelectedItem();
                if (user != null) {
                    service.deleteUser(user);
                    usersList.getItems().remove(user);
                }
            }
        });

    }

    private boolean validate() {
        if (StringUtils.isBlank(nameField.getText()) || StringUtils.isBlank(loginField.getText())
                || StringUtils.isBlank(passwordField.getText()) || StringUtils.isBlank(repeatField.getText())) {
            errorLabel.setText("Заполните все поля!");
            errorLabel.setVisible(true);
            return false;
        }
        if (service.getUser(loginField.getText()).getLogin() != null) {
            errorLabel.setText(String.format("Введите другой логин, %s существует в базе", loginField.getText()));
            errorLabel.setVisible(true);
            return false;
        }
        if (!passwordField.getText().equals(repeatField.getText())) {
            errorLabel.setText("Пароли не совпадают!");
            errorLabel.setVisible(true);
            return false;
        }
        errorLabel.setVisible(false);
        return true;
    }
}
