package com.sun.manager.events;

import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.NumericData;
import com.sun.manager.dto.ResData;
import com.sun.manager.forms.ButtonCell;
import com.sun.manager.forms.EditingCell;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

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
                int index = input.indexOf(":");
                data.setMinutes(Long.valueOf(input.substring(0, index).trim()));
                if (input.contains("$")) {
                    data.setTotalPrice(Long.valueOf(input.substring(input.indexOf("$") + 1).trim()));
                } else {
                    data.setAbonementNumber(input.substring(index + 1).trim());
                }

                data.setRes(input);
            }
        };
    }

    public static Callback cellFactoryForBaseSolarium(final DataColumnEnum e) {
        return new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new EditingCell<BaseSolariumData>(e);
            }
        };
    }

    public static Callback cellFactoryNum(final DataColumnEnum e) {
        return new Callback<TableColumn<NumericData, String>, TableCell<NumericData, String>>() {
            @Override
            public TableCell<NumericData, String> call(TableColumn<NumericData, String> p) {

                return new EditingCell<NumericData>(e);
            }

        };
    }

    public static Callback cellFactoryBaseRes(final DataColumnEnum e) {
        return new Callback<TableColumn<ResData, String>, TableCell<ResData, String>>() {
            @Override
            public TableCell<ResData, String> call(TableColumn<ResData, String> p) {

                return new EditingCell<ResData>(e);
            }

        };
    }

    // TODO temp: need to create factories for cosmetics snd abonements when dao will be created
    public static Callback cellFactoryForCosmAbon() {
        return new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new ButtonCell<BaseSolariumData>();
            }
        };
    }
}
