package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.events.ClosePageEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.Date;

/**
 * User: iason
 * Date: 17.03.14
 */
public class ShowCommentPage extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(UsersPage.class.getResource("fxml/show_comments.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Комментарии");
        stage.show();
    }
}