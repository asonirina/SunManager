package com.sun.manager.events;

import com.sun.manager.dto.AvailableAbonements;

/**
 * User: iason
 * Date: 18.11.14
 */
public class AvailableAbonementsCreatedEvent {
    AvailableAbonements abon;

    public AvailableAbonementsCreatedEvent(AvailableAbonements abon) {
        this.abon = abon;
    }

    public AvailableAbonements getAbon() {
        return abon;
    }
}
