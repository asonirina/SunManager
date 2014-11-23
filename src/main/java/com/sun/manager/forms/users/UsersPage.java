package com.sun.manager.forms.users;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UsersPage extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(UsersPage.class.getResource("fxml/users_page.fxml"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("../css/general.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Администраторы");
        stage.show();

    }

}