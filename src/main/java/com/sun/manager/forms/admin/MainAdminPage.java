package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.connection.SqlServer;
import com.sun.manager.events.ClosePageEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * User: iason
 */
public class MainAdminPage extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SqlServer.init(); //todo put it to login page
        Pane pane = FXMLLoader.load(MainAdminPage.class.getResource("fxml/admin_main1.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Main Page");
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
