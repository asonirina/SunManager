package com.sun.manager.forms.admin;

import com.sun.manager.App;
import com.sun.manager.dto.Comment;
import com.sun.manager.service.SolariumService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 17.03.14
 */
public class ShowCommentController extends AnchorPane implements Initializable {
    @FXML
    TextArea commentArea;

    SolariumService service = new SolariumService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Comment> comments = service.getComments(App.getInstance().getSelectedDate());
        String text = "";
        for (Comment comment: comments) {
          text+=comment.getComment()+"\n\n";
        }
        commentArea.setText(text);

    }


}

