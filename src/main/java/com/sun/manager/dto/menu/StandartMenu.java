package com.sun.manager.dto.menu;

public class StandartMenu {
    String menuID;
    String parentMenuID;
    String description;
    String userRole;

    public StandartMenu(String menuID, String parentMenuID, String description, String userRole) {
        this.menuID = menuID;
        this.parentMenuID = parentMenuID;
        this.description = description;
        this.userRole = userRole;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentMenuID() {
        return parentMenuID;
    }

    public void setParentMenuID(String parentMenuID) {
        this.parentMenuID = parentMenuID;
    }
}
