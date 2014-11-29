package com.sun.manager.forms.abons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AbonementPage extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(AbonementPage.class.getResource("fxml/abonement.fxml"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Доступные абонементы");
        stage.show();
    }

}