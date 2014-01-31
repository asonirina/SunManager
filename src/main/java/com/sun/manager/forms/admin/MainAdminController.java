package com.sun.manager.forms.admin;


import com.google.common.eventbus.Subscribe;
import com.sun.manager.App;
import com.sun.manager.constants.BlankItem;
import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.*;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.events.ClosePageEvent;
import com.sun.manager.events.EventHandlers;
import com.sun.manager.events.NewAbonementAddedEvent;
import com.sun.manager.events.NewCosmeticsAddedEvent;
import com.sun.manager.forms.EditingCell;
import com.sun.manager.forms.ButtonCell;
import com.sun.manager.service.SolariumService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.Destroyable;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * User: iason
 */
public class MainAdminController extends AnchorPane implements Initializable {
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

    int cosmeticsDataSize = 0;

    int abonementsDataSize = 0;

    SolariumService solariumService = new SolariumService();
    final ObservableList<BaseSolariumData> vertData = FXCollections.observableArrayList(
            solariumService.getSunData(new Date(Calendar.getInstance().getTime().getTime()), SolariumEnum.Vertical));

    final ObservableList<BaseSolariumData> greenData = FXCollections.observableArrayList(
            solariumService.getSunData(new Date(Calendar.getInstance().getTime().getTime()), SolariumEnum.Green));

    final ObservableList<BaseSolariumData> blueData = FXCollections.observableArrayList(
            solariumService.getSunData(new Date(Calendar.getInstance().getTime().getTime()), SolariumEnum.Blue));

    final ObservableList<CosmeticsRequest> cosmeticsData = FXCollections.observableArrayList();

    final ObservableList<AbonementsRequest> abonementsData = FXCollections.observableArrayList();


    final ObservableList<ResData> vertResData = FXCollections.observableArrayList(
            new ResData("Итого мин: "), new ResData("Итого руб: "), new ResData("L2= "));
    final ObservableList<ResData> greenResData = FXCollections.observableArrayList(
            new ResData("Итого мин: "), new ResData("Итого руб: "), new ResData("L2= "));

    final ObservableList<ResData> blueResData = FXCollections.observableArrayList(
            new ResData("Итого мин: "), new ResData("Итого руб: "), new ResData("L2= "));

    final ObservableList<ResData> cosmResData = FXCollections.observableArrayList(
            new ResData("стикини: 0 шт"), new ResData("к-ка итого:"), new ResData("к-ка+стикини:"));

    final ObservableList<ResData> abonResData = FXCollections.observableArrayList(null, new ResData("итого:"), null);

    int vertSize = vertData.size();
    int greenSize = greenData.size();
    int blueSize = blueData.size();


    // new Date(Calendar.getInstance().getTime().getTime()); - current date

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            App.getInstance().getEventBus().register(this);

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
        tableGreen.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlue.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableVert.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableCosm.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbon.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableNumber.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

        tableNumRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableVertRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableGreenRes.getStylesheets().add(this.getClass().getResource("styleGreen.css").toExternalForm());
        tableBlueRes.getStylesheets().add(this.getClass().getResource("styleBlue.css").toExternalForm());
        tableCosmRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());
        tableAbonRes.getStylesheets().add(this.getClass().getResource("styleNormal.css").toExternalForm());

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
        tableVert.setEditable(true);
        tableGreen.setEditable(true);
        tableBlue.setEditable(true);

        tableCosmRes.setEditable(true);
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

        for (int i = 0; i < 30; ++i) {
            cosmeticsData.add((CosmeticsRequest) BlankItem.generateBlankItem(2L));
        }

        for (int i = 0; i < 30; ++i) {
            abonementsData.add((AbonementsRequest) BlankItem.generateBlankItem(3L));
        }
    }

    private void setOnButtonsClicked() {
        countVert.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                countSolariumData(vertData, vertResData);
            }
        });

        countGreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                countSolariumData(vertData, vertResData);
            }
        });

        countBlue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                countSolariumData(vertData, vertResData);
            }
        });

        countCosm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Long count = 0L;
                for (CosmeticsRequest cr : cosmeticsData) {
                    if (cr.getCount() != null) {
                        count += cr.getCount();
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
                abonResData.set(1, new ResData("итого: " + abonementsDataSize));
            }
        });


    }


    private void countSolariumData(ObservableList<BaseSolariumData> data, ObservableList<ResData> resData) {
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
        Long l2 = solariumService.getL2ById(1L) + sum;
        resData.set(2, new ResData("L2= " + l2));
    }

    @Subscribe
    public void newCosmeticsAdded(NewCosmeticsAddedEvent e) {
        ObservableList<CosmeticsRequest> list = e.getList();
        for (CosmeticsRequest cr : list) {
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
        ObservableList<BaseSolariumData> data = FXCollections.observableArrayList();
        for (int i = vertSize; i < 30; ++i) {
            BaseSolariumData d = vertData.get(i);
            if (d.getMinutes() != null) {
                data.add(d);
            }
        }
        solariumService.saveSolariumData(data, 1L);


        data = FXCollections.observableArrayList();
        for (int i = greenSize; i < 30; ++i) {
            BaseSolariumData d = greenData.get(i);
            if (d.getMinutes() != null) {
                data.add(d);
            }
        }
        solariumService.saveSolariumData(data, 2L);


        data = FXCollections.observableArrayList();
        for (int i = blueSize; i < 30; ++i) {
            BaseSolariumData d = blueData.get(i);
            if (d.getMinutes() != null) {
                data.add(d);
            }
        }
        solariumService.saveSolariumData(data, 3L);
    }

}
