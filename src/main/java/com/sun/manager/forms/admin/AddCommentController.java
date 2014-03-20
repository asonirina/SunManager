package com.sun.manager.forms.admin;

import com.sun.manager.dto.Comment;
import com.sun.manager.service.SolariumService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * User: iason
 * Date: 17.03.14
 */
public class AddCommentController extends AnchorPane implements Initializable {
    @FXML
    TextArea commentArea;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    @FXML
    Label errorLabel;

    SolariumService service = new SolariumService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (validate()) {
                    Comment comment = new Comment();
                    comment.setComment(commentArea.getText());
                    comment.setDate(new Date());
                    service.saveComment(comment);
                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                }
            }
        });

        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });

    }

    private boolean validate() {
        if (StringUtils.isBlank(commentArea.getText())) {
            errorLabel.setText("Добавьте комментарий!");
            errorLabel.setVisible(true);
            return false;
        }
        errorLabel.setVisible(false);
        return true;
    }

}

