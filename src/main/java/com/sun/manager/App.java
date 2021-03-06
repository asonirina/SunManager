package com.sun.manager;

import com.google.common.eventbus.EventBus;
import com.sun.manager.dto.Users;

import java.sql.Date;

public class App {
    private static App ourInstance = new App();
    private EventBus eventBus = new EventBus();
    private Users user = null;
    private Date selectedDate;

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
}
