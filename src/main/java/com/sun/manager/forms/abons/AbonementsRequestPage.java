package com.sun.manager.forms.abons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User: iason
 * Date: 30.01.14
 */
public class AbonementsRequestPage extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(AbonementsRequestPage.class.getResource("fxml/abonement_page.fxml"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Покупка абонемента");
        stage.show();
    }
}
