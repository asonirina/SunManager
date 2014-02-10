package com.sun.manager.forms.login;

import com.sun.manager.connection.SqlServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User: iason
 */
public class LoginPage extends Application {

    public static void main(String[] args) {
        SqlServer.init();
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SqlServer.init();
        Pane pane = FXMLLoader.load(LoginPage.class.getResource("fxml/login.fxml"));
        stage.setScene(new Scene(pane));
        stage.setTitle("Login");
        stage.show();
    }
}
