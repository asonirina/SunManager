package com.sun.manager.forms.login;

import com.sun.manager.App;
import com.sun.manager.dto.Users;
import com.sun.manager.forms.admin.MainAdminPage;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.UsersService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: iason
 */

public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button login;

    UsersService usersService = new UsersService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    String username = loginField.getText();
                    String pwd = passwordField.getText();

                    if (usersService.login(username, pwd)) {
                        Users user = usersService.getUser(username);
                        App.getInstance().setUser(user);
                        MainAdminPage page = new MainAdminPage();

                        page.start(new Stage());

                        ((Stage) loginField.getScene().getWindow()).close();
                    } else {
                        new AlertDialog((Stage)login.getScene().getWindow(),"Логин или пароль введены не верно!", 1).showAndWait();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    new AlertDialog((Stage)login.getScene().getWindow(),"Невозможно загрузить приложение!", 1).showAndWait();
                }
            }
        });


    }


}

