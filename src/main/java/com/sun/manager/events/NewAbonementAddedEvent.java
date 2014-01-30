package com.sun.manager.events;

import com.sun.manager.dto.AbonementsRequest;

/**
 * User: iason
 * Date: 30.01.14
 */
public class NewAbonementAddedEvent implements Event {
    private AbonementsRequest request;

    public NewAbonementAddedEvent(AbonementsRequest request) {
        this.request = request;
    }

    public AbonementsRequest getRequest() {
        return request;
    }

}
