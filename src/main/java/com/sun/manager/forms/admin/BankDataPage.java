package com.sun.manager.forms.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BankDataPage extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(BankDataPage.class.getResource("fxml/bank_data.fxml"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Касса за день");
        stage.show();
    }

}