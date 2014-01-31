package com.sun.manager.events;

import com.sun.manager.constants.DataColumnEnum;
import com.sun.manager.dto.*;
import com.sun.manager.forms.ButtonCell;
import com.sun.manager.forms.EditingCell;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;

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

    public static EventHandler eventHandlerCosmeticsEditCommit() {
        return new EventHandler<TableColumn.CellEditEvent<ResData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ResData, String> t) {
                ResData data =
                        (ResData) t.getTableView().getItems().get(t.getTablePosition().getRow());
                String input = t.getNewValue();
                data.setRes(input);

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
}
