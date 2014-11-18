package com.sun.manager.events;

import com.sun.manager.constants.BlankItem;
import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.dto.*;
import com.sun.manager.forms.*;
import com.sun.manager.forms.alert.AlertDialog;
import com.sun.manager.service.SolariumService;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.util.Calendar;

/**
 * User: iason
 */
public class EventHandlers {

    public static EventHandler eventHandlerBaseSolariumEditCommit() {
        return new EventHandler<TableColumn.CellEditEvent<BaseSolariumData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BaseSolariumData, String> t) {
                BaseSolariumData data =
                        (BaseSolariumData) t.getTableView().getItems().get(t.getTablePosition().getRow());
                data.setTotalPrice(null);
                data.setAbonementNumber(null);
                String input = t.getNewValue();
                if (StringUtils.isBlank(input)) {
                    return;
                }
                try {
                    int index = input.indexOf(":");
                    data.setMinutes(Long.valueOf(input.substring(0, index).trim()));
                    if (input.contains("$")) {
                        data.setTotalPrice(Long.valueOf(input.substring(input.indexOf("$") + 1).trim()));
                    } else {
                        data.setAbonementNumber(input.substring(index + 1).trim());
                    }


                    data.setRes(input);
                    data.setStartDate(new Date(Calendar.getInstance().getTime().getTime()));
                } catch (Exception ex) {
                    Stage stage = new Stage();
                    stage.centerOnScreen();
                    new AlertDialog(null, "Введите значение в правильном формате!", 1).showAndWait();
                }

            }
        };
    }

    public static EventHandler eventHandlerCosmeticsCommit(final SolariumService service) {
        return new EventHandler<TableColumn.CellEditEvent<Cosmetics, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cosmetics, Long> t) {
                Cosmetics data = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Long count = t.getNewValue();
                data.setCount(count);
                service.updateCosmetics(data);
            }
        };
    }

    public static EventHandler eventHandlerAbonementsPrice() {
        return new EventHandler<TableColumn.CellEditEvent<AvailableAbonements, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AvailableAbonements, Integer> t) {
                AvailableAbonements data = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Integer price = t.getNewValue();
                data.setPrice(price);
            }
        };
    }

    public static EventHandler eventHandlerAbonementsMinutes() {
        return new EventHandler<TableColumn.CellEditEvent<AvailableAbonements, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AvailableAbonements, Integer> t) {
                AvailableAbonements data = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Integer minutes = t.getNewValue();
                data.setMinutes(minutes);
            }
        };
    }

    public static EventHandler eventHandlerAbonementsDuration() {
        return new EventHandler<TableColumn.CellEditEvent<AvailableAbonements, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AvailableAbonements, Integer> t) {
                AvailableAbonements data = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Integer duration = t.getNewValue();
                data.setDuration(duration);
            }
        };
    }

    public static EventHandler eventHandlerAbonementsTime() {
        return new EventHandler<TableColumn.CellEditEvent<AvailableAbonements, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AvailableAbonements, Integer> t) {
                AvailableAbonements data = t.getTableView().getItems().get(t.getTablePosition().getRow());
                Integer time = t.getNewValue();
                data.setAvailableTime(time);
            }
        };
    }

    public static EventHandler eventHandlerResDataEditCommit() {
        return new EventHandler<TableColumn.CellEditEvent<ResData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ResData, String> t) {
                ResData data =
                        (ResData) t.getTableView().getItems().get(t.getTablePosition().getRow());
                String input = t.getNewValue();
                data.setRes(input);

            }
        };
    }

    public static Callback cellFactoryForBaseSolarium(final DataColumnEnum e) {
        return new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new EditingCellSolariumData<BaseSolariumData, String>(e);
            }
        };
    }

    public static Callback cellFactoryNum() {
        return new Callback<TableColumn<NumericData, String>, TableCell<NumericData, String>>() {
            @Override
            public TableCell<NumericData, String> call(TableColumn<NumericData, String> p) {
                return new EditingCellSimple<NumericData, String>();
            }

        };
    }

    public static Callback cellFactoryRes() {
        return new Callback<TableColumn<ResData, String>, TableCell<ResData, String>>() {
            @Override
            public TableCell<ResData, String> call(TableColumn<ResData, String> p) {
                return new EditingCellSimple<ResData, String>();
            }

        };
    }

    public static Callback cellFactoryL2() {
        return new Callback<TableColumn<ResData, String>, TableCell<ResData, String>>() {
            @Override
            public TableCell<ResData, String> call(TableColumn<ResData, String> p) {
                return new EditingCellL2<ResData, String>();
            }

        };
    }

    public static Callback cellFactoryForStikini() {
        return new Callback<TableColumn<CosmeticsRequest, String>, TableCell<CosmeticsRequest, String>>() {
            @Override
            public TableCell<CosmeticsRequest, String> call(TableColumn<CosmeticsRequest, String> p) {
                return new EditingCellStikini<CosmeticsRequest, String>();
            }
        };
    }

    public static Callback cellFactoryForCosmetics() {
        return new Callback<TableColumn<CosmeticsRequest, String>, TableCell<CosmeticsRequest, String>>() {
            @Override
            public TableCell<CosmeticsRequest, String> call(TableColumn<CosmeticsRequest, String> p) {
                return new ButtonCell<CosmeticsRequest>(DataColumnEnum.Cosmetics);
            }
        };
    }

    public static Callback cellFactoryForAbonements() {
        return new Callback<TableColumn<AbonementsRequest, String>, TableCell<AbonementsRequest, String>>() {
            @Override
            public TableCell<AbonementsRequest, String> call(TableColumn<AbonementsRequest, String> p) {
                return new ButtonCell<AbonementsRequest>(DataColumnEnum.Abonements);
            }
        };
    }

    public static Callback cellFactoryForEditCosmetics() {
        return new Callback<TableColumn<Cosmetics, Long>, TableCell<Cosmetics, Long>>() {
            @Override
            public TableCell<Cosmetics, Long> call(TableColumn<Cosmetics, Long> p) {
                return new EditingCellAddCosmetics<Cosmetics, Long>();
            }
        };
    }

    public static Callback cellFactoryAvailableAbonements() {
        return new Callback<TableColumn<AvailableAbonements, Integer>, TableCell<AvailableAbonements, Integer>>() {
            @Override
            public TableCell<AvailableAbonements, Integer> call(TableColumn<AvailableAbonements, Integer> p) {
                return new EditingCellAbonements<AvailableAbonements, Integer>();
            }
        };
    }


    public static Callback cellFactoryLetter() {
        return new Callback<TableColumn<AvailableAbonements, String>, TableCell<AvailableAbonements, String>>() {
            @Override
            public TableCell<AvailableAbonements, String> call(TableColumn<AvailableAbonements, String> p) {
                TableCell<AvailableAbonements, String> cell = new TableCell<AvailableAbonements, String>();
                cell.setPrefHeight(27);
                return cell;
            }

        };
    }
}
