package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.events.ClosePageEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class UsersPage extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(UsersPage.class.getResource("fxml/users_page.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Пользователи");
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    App.getInstance().getEventBus().post(new ClosePageEvent());
                    stop();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}