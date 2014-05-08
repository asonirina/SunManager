package com.sun.manager.forms.admin;


import com.google.common.eventbus.Subscribe;
import com.sun.manager.App;
import com.sun.manager.constants.BlankItem;
import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.dto.*;
import com.sun.manager.events.ClosePageEvent;
import com.sun.manager.events.EventHandlers;
import com.sun.manager.events.NewAbonementAddedEvent;
import com.sun.manager.events.NewCosmeticsAddedEvent;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.forms.login.LoginPage;
import com.sun.manager.service.SolariumService;
import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * User: iason
 */
public class MainAdminController extends ScrollPane implements Initializable {
    private Date date = new Date(Calendar.getInstance().getTime().getTime());

    @FXML
    Button saveChanges;

    @FXML
    Button logout;
    @FXML
    AnchorPane anchorPane;

    @FXML
    Label dateLabel;

    @FXML
    Button addComment;

    @FXML
    Button showComments;

    @FXML
    Button statButton;

    @FXML
    Label usernameLabel;


    DatePicker datePicker = new DatePicker();
    //main tables
    @FXML
    TableView<NumericData> tableNumber;

    @FXML
    TableView<BaseSolariumData> tableVert;

    @FXML
    TableView<BaseSolariumData> tableGreen;

    @FXML
    TableView<BaseSolariumData> tableBlue;

    @FXML
    TableView<CosmeticsRequest> tableCosm;

    @FXML
    TableView<AbonementsRequest> tableAbon;


    // main columns
    @FXML
    TableColumn colNumber;

    @FXML
    TableColumn colVert;

    @FXML
    TableColumn colGreen;

    @FXML
    TableColumn colBlue;

    @FXML
    TableColumn colCosm;

    @FXML
    TableColumn colAbon;

    //// tables with res data
    @FXML
    TableView<ResData> tableNumRes;

    @FXML
    TableColumn colNumRes;

    @FXML
    TableView<ResData> tableVertRes;

    @FXML
    TableColumn colVertRes;

    @FXML
    TableView<ResData> tableGreenRes;

    @FXML
    TableColumn colGreenRes;

    @FXML
    TableView<ResData> tableBlueRes;

    @FXML
    TableColumn colBlueRes;

    @FXML
    TableView<ResData> tableCosmRes;

    @FXML
    TableColumn colCosmRes;

    @FXML
    TableView<ResData> tableAbonRes;

    @FXML
    TableColumn colAbonRes;

    @FXML
    Button countVert;

    @FXML
    Button countGreen;

    @FXML
    Button countCosm;

    @FXML
    Button countAbon;

    @FXML
    Button countBlue;

    @FXML
    Button addCosm;

    @FXML
    Button addAbon;

    @FXML
    Button usersButton;

    @FXML
    Button bankButton;

    @FXML
    Button del1;

    @FXML
    Button del2;

    @FXML
    Button del3;


    SolariumService solariumService = new SolariumService();
    final ObservableList<BaseSolariumData> vertData = FXCollections.observableArrayList(
            solariumService.getSunData(date, SolariumEnum.Vertical));

    final ObservableList<BaseSolariumData> greenData = FXCollections.observableArrayList(
            solariumService.getSunData(date, SolariumEnum.Green));

    final ObservableList<BaseSolariumData> blueData = FXCollections.observableArrayList(
            solariumService.getSunData(date, SolariumEnum.Blue));

    final ObservableList<CosmeticsRequest> cosmeticsData = FXCollections.observableArrayList(solariumService.getCosmByDate(date));

    final ObservableList<AbonementsRequest> abonementsData = FXCollections.observableArrayList(solariumService.getAbonByDate(date));


    final ObservableList<ResData> vertResData = FXCollections.observableArrayList(
            new ResData("Итого мин: "), new ResData("Итого руб: "), new ResData("L2= "));
    final ObservableList<ResData> greenResData = FXCollections.observableArrayList(
            new ResData("Итого мин: "), new ResData("Итого руб: "), new ResData("L2= "));

    final ObservableList<ResData> blueResData = FXCollections.observableArrayList(
            new ResData("Итого мин: "), new ResData("Итого руб: "), new ResData("L2= "));

    final ObservableList<ResData> cosmResData = FXCollections.observableArrayList(
            new ResData("стикини: 0 шт"), new ResData("к-ка итого:"), new ResData("к-ка+стикини:"));

    final ObservableList<ResData> abonResData = FXCollections.observableArrayList(null, new ResData("итого:"), null);

    int cosmeticsDataSize = cosmeticsData.size();
    int abonementsDataSize = abonementsData.size();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (App.getInstance().getUser().getRole().equals("derictor")) {
                datePicker.setLayoutX(49);
                datePicker.setLayoutY(46);

                datePicker.getStylesheets().add(this.getClass().getResource("datePicker.css").toExternalForm());
                datePicker.getCalendarView().setShowWeeks(false);
                datePicker.setSelectedDate(new java.util.Date());
                datePicker.getCalendarView().todayButtonTextProperty().set("Сегодня");
                datePicker.selectedDateProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        date = new Date(datePicker.getSelectedDate().getTime());
                        updateInfoByDate();
                        App.getInstance().setSelectedDate(date);


                    }
                });
                anchorPane.getChildren().add(datePicker);

                dateLabel.setVisible(false);
            }
            App.getInstance().setSelectedDate(date);
            App.getInstance().getEventBus().register(this);

            dateLabel.setText((new Date(Calendar.getInstance().getTime().getTime()).toString()));
            usernameLabel.setText("Добро пожаловать, " + App.getInstance().getUser().toString() + "!");
            setOnButtonsClicked();
            addBlankItems();

            setEditableTables();

            setColumnFactory();
            setCellFactory();
            setStyles();

            setOnEdit();

            setNumbers();
            setNumResData();
            setMainData();
            setSolariumResData();
            setCosmResData();
            setAbonResData();

        } catch (Exception ex) {
            new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
            ex.printStackTrace();
        }

    }

    private void setColumnFactory() {
        colGreen.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colVert.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colBlue.setCellValueFactory(new PropertyValueFactory<BaseSolariumData, String>("res"));
        colCosm.setCellValueFactory(new PropertyValueFactory<CosmeticsRequest, String>("res"));
        colAbon.setCellValueFactory(new PropertyValueFactory<AbonementsRequest, String>("res"));
        colNumber.setCellValueFactory(new PropertyValueFactory<NumericData, String>("data"));

        colNumRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colVertRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colGreenRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colBlueRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colCosmRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));
        colAbonRes.setCellValueFactory(new PropertyValueFactory<ResData, String>("res"));


    }

    private void setCellFactory() {

        colVert.setCellFactory(EventHandlers.cellFactoryForBaseSolarium(DataColumnEnum.VerticalSolarium));
        colGreen.setCellFactory(EventHandlers.cellFactoryForBaseSolarium(DataColumnEnum.GreenSolarium));
        colBlue.setCellFactory(EventHandlers.cellFactoryForBaseSolarium(DataColumnEnum.BlueSolarium));

        colCosm.setCellFactory(EventHandlers.cellFactoryForCosmetics());
        colAbon.setCellFactory(EventHandlers.cellFactoryForAbonements());

        colNumber.setCellFactory(EventHandlers.cellFactoryNum(DataColumnEnum.Number));

        colNumRes.setCellFactory(EventHandlers.cellFactoryBaseRes(DataColumnEnum.Number));
        colVertRes.setCellFactory(EventHandlers.cellFactoryBaseRes(DataColumnEnum.VerticalSolarium));
        colGreenRes.setCellFactory(EventHandlers.cellFactoryBaseRes(DataColumnEnum.GreenSolarium));
        colBlueRes.setCellFactory(EventHandlers.cellFactoryBaseRes(DataColumnEnum.BlueSolarium));
        colCosmRes.setCellFactory(EventHandlers.cellFactoryBaseRes(DataColumnEnum.Cosmetics));
        colAbonRes.setCellFactory(EventHandlers.cellFactoryBaseRes(DataColumnEnum.Abonements));
    }

    private void setStyles() {
//        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
//        tableBlue.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
//        tableVert.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//        tableCosm.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//        tableAbon.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//        tableNumber.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//
//        tableNumRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//        tableVertRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//        tableGreenRes.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
//        tableBlueRes.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
//        tableCosmRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
//        tableAbonRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

    }

    private void setOnEdit() {
        colGreen.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        colBlue.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());
        colVert.setOnEditCommit(EventHandlers.eventHandlerBaseSolariumEditCommit());

        colCosmRes.setOnEditCommit(EventHandlers.eventHandlerCosmeticsEditCommit());
    }

    private void setNumbers() {
        NumericData[] numericDatas = new NumericData[30];
        for (int i = 0; i < 30; i++) {
            numericDatas[i] = new NumericData(i + 1);
        }
        final ObservableList<NumericData> nums = FXCollections.observableArrayList(
                numericDatas);
        tableNumber.setItems(nums);

    }

    private void setNumResData() {
        final ObservableList<ResData> resDatas2 = FXCollections.observableArrayList(null, null, null);
        tableNumRes.setItems(resDatas2);
    }

    private void setSolariumResData() {
        tableVertRes.setItems(vertResData);
        tableGreenRes.setItems(greenResData);
        tableBlueRes.setItems(blueResData);

    }

    private void setMainData() {
        tableVert.setItems(vertData);
        tableGreen.setItems(greenData);
        tableBlue.setItems(blueData);
        tableCosm.setItems(cosmeticsData);
        tableAbon.setItems(abonementsData);
    }

    private void setEditableTables() {
        if (App.getInstance().getUser().getRole().equals("derictor")) {
            tableVert.setEditable(false);
            tableGreen.setEditable(false);
            tableBlue.setEditable(false);

            tableCosmRes.setEditable(false);
        }
    }

    private void setCosmResData() {
        tableCosmRes.setItems(cosmResData);
    }

    private void setAbonResData() {
        tableAbonRes.setItems(abonResData);
    }

    private void addBlankItems() {
        int size = vertData.size();
        for (int i = size; i < 30; ++i) {
            vertData.add((BaseSolariumData) BlankItem.generateBlankItem(1L));
        }


        size = greenData.size();
        for (int i = size; i < 30; ++i) {
            greenData.add((BaseSolariumData) BlankItem.generateBlankItem(1L));
        }

        size = blueData.size();
        for (int i = size; i < 30; ++i) {
            blueData.add((BaseSolariumData) BlankItem.generateBlankItem(1L));
        }

        size = cosmeticsData.size();
        for (int i = size; i < 30; ++i) {
            cosmeticsData.add((CosmeticsRequest) BlankItem.generateBlankItem(2L));
        }

        size = abonementsData.size();
        for (int i = size; i < 30; ++i) {
            abonementsData.add((AbonementsRequest) BlankItem.generateBlankItem(3L));
        }
    }

    private void setOnButtonsClicked() {
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoginPage page = new LoginPage();
                try {
                    page.start(new Stage());
                    ((Stage) logout.getScene().getWindow()).close();
                } catch (IOException ex) {
                    new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                    //throw new RuntimeException(ex);
                }
            }
        });
        countVert.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                countSolariumData(vertData, vertResData, 1L);
            }
        });

        countGreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                countSolariumData(greenData, greenResData, 2L);
            }
        });

        countBlue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                countSolariumData(blueData, blueResData, 3L);
            }
        });

        countCosm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Long count = 0L;
                for (CosmeticsRequest cr : cosmeticsData) {
                    if (cr.getCount() != null) {
                        count += cr.getCosmetics().getPrice();
                    }
                }

                cosmResData.set(1, new ResData("к-ка итого: " + count));

                int stikini = new Scanner(cosmResData.get(0).getRes()).useDelimiter("\\D+").nextInt();

                cosmResData.set(2, new ResData("к-ка+стикини: " + (stikini + count)));
            }
        });

        countAbon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Long count = 0L;
                for (AbonementsRequest ar : abonementsData) {
                    if (ar.getPrice() != null) {
                        count += ar.getPrice();
                    }
                }
                abonResData.set(1, new ResData("итого: " + count));
            }
        });

        bankButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                App.getInstance().getEventBus().post(new ClosePageEvent());
                BankDataPage page = new BankDataPage();
                try {
                    page.start(new Stage());
                } catch (IOException ex) {
                    new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                    //throw new RuntimeException(ex);
                }
            }
        });

        if (App.getInstance().getUser().getRole().equals("admin")) {
            del1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BaseSolariumData data = tableVert.getSelectionModel().getSelectedItem();
                    if (data != null) {
                        if (data.isSaved()) {
                            solariumService.deleteSolariumData(data, 1L);
                        }
                        vertData.remove(data);
                        vertData.add((BaseSolariumData) BlankItem.generateBlankItem(1L));
                    }
                }
            });
            del2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BaseSolariumData data = tableGreen.getSelectionModel().getSelectedItem();
                    if (data != null) {
                        if (data.isSaved()) {
                            solariumService.deleteSolariumData(data, 2L);
                        }
                        greenData.remove(data);
                        greenData.add((BaseSolariumData) BlankItem.generateBlankItem(1L));
                    }
                }
            });
            del3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BaseSolariumData data = tableBlue.getSelectionModel().getSelectedItem();
                    if (data != null) {
                        if (data.isSaved()) {
                            solariumService.deleteSolariumData(data, 3L);
                        }
                        blueData.remove(data);
                        blueData.add((BaseSolariumData) BlankItem.generateBlankItem(1L));
                    }
                }
            });
            addCosm.setVisible(false);
            addAbon.setVisible(false);
            usersButton.setVisible(false);
            showComments.setVisible(false);
            statButton.setVisible(false);
            addComment.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    AddCommentPage page = new AddCommentPage();
                    try {
                        page.start(new Stage());
                    } catch (IOException ex) {
                        new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                    }
                }
            });
            saveChanges.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    destroy(new ClosePageEvent());

                    new AlertDialog((Stage) logout.getScene().getWindow(), "Изменения успешно сохранены!", 0).showAndWait();

                }
            });
        } else {
            saveChanges.setVisible(false);
            addComment.setVisible(false);
            showComments.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ShowCommentPage page = new ShowCommentPage();
                    try {

                        page.start(new Stage());
                    } catch (IOException ex) {
                        new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                        //throw new RuntimeException(ex);
                    }
                }
            });
            addCosm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    AddCosmeticsPage page = new AddCosmeticsPage();
                    try {
                        page.start(new Stage());
                    } catch (IOException ex) {
                        new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                        //throw new RuntimeException(ex);
                    }
                }
            });
            addAbon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        AddAbonementPage page = new AddAbonementPage();
                        page.start(new Stage());
                    } catch (IOException ex) {
                        new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                        throw new RuntimeException(ex);
                    }
                }
            });

            usersButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        UsersPage page = new UsersPage();
                        page.start(new Stage());
                    } catch (IOException ex) {
                        new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                        //throw new RuntimeException(ex);
                    }
                }
            });

            statButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        StatisticsPage page = new StatisticsPage();
                        page.start(new Stage());
                    } catch (IOException ex) {
                        new AlertDialog((Stage) logout.getScene().getWindow(), "Произошла ошибка!", 1).showAndWait();
                        throw new RuntimeException(ex);
                    }
                }
            });
        }


    }


    private void countSolariumData(ObservableList<BaseSolariumData> data, ObservableList<ResData> resData, long num) {
        Long count = 0L;
        Long sum = 0L;
        for (BaseSolariumData d : data) {
            if (d.getMinutes() != null) {
                count += d.getMinutes();
            }

            if (d.getTotalPrice() != null) {
                sum += d.getTotalPrice();
            }
        }
        resData.set(0, new ResData("Итого мин: " + count));
        resData.set(1, new ResData("Итого руб: " + sum));
        Double l2 = Double.valueOf(count / 60 + "." + ((count >= 10) ? "" : "0") + count % 60)+solariumService.getL2ById(num);
        resData.set(2, new ResData("L2= " + l2));
    }

    @Subscribe
    public void newCosmeticsAdded(NewCosmeticsAddedEvent e) {
        ObservableList<CosmeticsRequest> list = e.getList();
        for (CosmeticsRequest cr : list) {
            cr.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
            cosmeticsData.set(cosmeticsDataSize++, cr);
        }
    }

    @Subscribe
    public void newAbonementAdded(NewAbonementAddedEvent e) {
        AbonementsRequest request = e.getRequest();
        abonementsData.set(abonementsDataSize++, request);
    }

    @Subscribe
    public void destroy(ClosePageEvent e) {
        if (App.getInstance().getUser().getRole().equals("admin")) {
            ObservableList<BaseSolariumData> data = FXCollections.observableArrayList();
            long minutes = 0;
            for (BaseSolariumData d : vertData) {
                if (d.getMinutes() != null && !d.isSaved()) {
                    minutes += d.getMinutes();
                    data.add(d);
                    d.setSaved(true);
                }
            }
            solariumService.saveSolariumData(data, 1L);
            solariumService.saveL2(1L, Double.valueOf(minutes / 60 + "." + ((minutes >= 10) ? "" : "0") + minutes % 60), App.getInstance().getSelectedDate(), minutes);
            minutes = 0;
            data = FXCollections.observableArrayList();
            for (BaseSolariumData d : greenData) {
                if (d.getMinutes() != null && !d.isSaved()) {
                    minutes += d.getMinutes();
                    data.add(d);
                    d.setSaved(true);
                }
            }
            solariumService.saveSolariumData(data, 2L);
            solariumService.saveL2(2L, Double.valueOf(minutes / 60 + "." + ((minutes >= 10) ? "" : "0") + minutes % 60), App.getInstance().getSelectedDate(), minutes);


            minutes = 0;
            data = FXCollections.observableArrayList();
            for (BaseSolariumData d : blueData) {
                if (d.getMinutes() != null && !d.isSaved()) {
                    minutes += d.getMinutes();
                    data.add(d);
                    d.setSaved(true);
                }
            }
            solariumService.saveSolariumData(data, 3L);
            solariumService.saveL2(3L, Double.valueOf(minutes / 60 + "." + ((minutes >= 10) ? "" : "0") + minutes % 60), App.getInstance().getSelectedDate(), minutes);


            ObservableList<CosmeticsRequest> cosmeticsRequests = FXCollections.observableArrayList();
            for (CosmeticsRequest cr : cosmeticsData) {
                if (cr.getCosmetics() != null && !cr.isSaved()) {
                    cosmeticsRequests.add(cr);
                    cr.setSaved(true);
                }
            }
            CosmeticsRequest cr = new CosmeticsRequest(cosmResData.get(0).getCount(), solariumService.getStikini());
            cosmeticsRequests.add(cr);
            solariumService.saveCosmetics(cosmeticsRequests);

        }
    }


    private void updateInfoByDate() {
        vertData.clear();
        vertData.addAll(0, solariumService.getSunData(date, SolariumEnum.Vertical));

        greenData.clear();
        greenData.addAll(0, solariumService.getSunData(date, SolariumEnum.Green));

        blueData.clear();
        blueData.addAll(0, solariumService.getSunData(date, SolariumEnum.Blue));

        cosmeticsData.clear();
        cosmeticsData.addAll(0, solariumService.getCosmByDate(date));

        abonementsData.clear();
        abonementsData.addAll(0, solariumService.getAbonByDate(date));

        addBlankItems();
    }

}
