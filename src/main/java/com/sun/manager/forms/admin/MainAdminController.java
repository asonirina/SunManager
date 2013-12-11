package com.sun.manager.forms.admin;

import com.sun.manager.dto.Users;
import com.sun.manager.dto.VerticalSun;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import sun.org.mozilla.javascript.internal.ast.NewExpression;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * User: iason
 */
public class MainAdminController extends AnchorPane implements Initializable {


    @FXML
    TableView<Users> table;

    @FXML
    TableColumn vertSun;

    @FXML
    TableColumn green;

    @FXML
    TableColumn blue;

    @FXML
    TableColumn cosm;

    @FXML
    TableColumn abon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setEditable(true);

        setColumnFactory();
        setStyles();
        //setEditing();

        Users user = new Users();
        user.setId("1");
        user.setName("lllll");

        final TextField addUser = new TextField();
        final ObservableList<Users> data = FXCollections.observableArrayList(
                user, user
        );

        table.setItems(data);


    }

    private void setColumnFactory() {
        vertSun.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
        green.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
        blue.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
        abon.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
        cosm.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));

    }

    private void setStyles() {
        green.setCellFactory(new Callback<TableColumn<Users, String>, TableCell<Users, String>>() {

            @Override
            public TableCell<Users, String> call(TableColumn<Users, String> p) {


                final TextFieldTableCell cell = new TextFieldTableCell<Users, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-background-color:DarkSeaGreen ; -fx-border-color: brown;");
                            setText(item);
                        }
                    }
                };

                cell.setConverter(new StringConverter() {
                    @Override
                    public String toString(Object o) {
                        return o.toString();
                    }

                    @Override
                    public Object fromString(String s) {
                        return s;
                    }
                });

                cell.setOnKeyReleased(
                        new EventHandler<KeyEvent>() {
                            public void handle(KeyEvent ke) {
                                System.out.println(ke.getText());
                                String s = cell.textProperty().getValue();
                                s+=ke.getText();
                                if (s.equals("5")) {
                                    System.out.println( );
                                    s+=": qqq";
                                    cell.updateItem(s, false);

                                }

                            }
                        }
                );
                return cell;
            }
        });

        blue.setCellFactory(new Callback<TableColumn<Users, String>, TableCell<Users, String>>() {

            @Override
            public TableCell<Users, String> call(TableColumn<Users, String> p) {


                return new TextFieldTableCell<Users, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-background-color:LightSkyBlue  ; -fx-border-color: brown;");
                            setText(item);
                        }
                    }
                };
            }
        });

        green.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Users, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Users, String> t) {
                        ((Users) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                    }
                }
        );


    }
}
