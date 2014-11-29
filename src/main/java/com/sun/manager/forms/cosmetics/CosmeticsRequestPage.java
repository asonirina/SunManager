package com.sun.manager.forms.cosmetics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User: iason
 */
public class CosmeticsRequestPage extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(CosmeticsRequestPage.class.getResource("fxml/cosmetics_request.fxml"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Косметика");
        stage.show();
    }
}
