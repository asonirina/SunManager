package com.sun.manager.events;

import com.sun.manager.dto.CosmeticsRequest;
import javafx.collections.ObservableList;

/**
 * User: iason
 * Date: 30.01.14
 */
public class NewCosmeticsAddedEvent implements Event {
    ObservableList<CosmeticsRequest> list;

    public NewCosmeticsAddedEvent(ObservableList<CosmeticsRequest> list) {
        this.list = list;
    }

    public ObservableList<CosmeticsRequest> getList() {
        return list;
    }

    public void setList(ObservableList<CosmeticsRequest> list) {
        this.list = list;
    }
}
