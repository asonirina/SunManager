package com.sun.manager.events;

import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.forms.ButtonCell;
import com.sun.manager.forms.EditingCell;
import javafx.event.EventHandler;
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

    public static Callback cellFactoryForBaseSolarium () {
        return new Callback<TableColumn<BaseSolariumData, String>, TableCell<BaseSolariumData, String>>() {
            @Override
            public TableCell<BaseSolariumData, String> call(TableColumn<BaseSolariumData, String> p) {
                return new EditingCell<BaseSolariumData>();
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
