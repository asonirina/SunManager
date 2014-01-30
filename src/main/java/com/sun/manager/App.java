package com.sun.manager;

import com.google.common.eventbus.EventBus;

/**
 * Hello world!
 */
public class App {
    private static App ourInstance = new App();
    private EventBus eventBus = new EventBus();

    public static App getInstance() {
        return ourInstance;
    }

    private App() {

    }

    public static void main(String[] args) {

    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
