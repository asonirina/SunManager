package com.sun.manager.dto.menu;

import java.util.ArrayList;
import java.util.List;

public class UserActivityMenu {

    private String menuId;
    private String description;
    private UserActivityMenu parentMenu;
    private List<UserActivityMenu> childrenMenuList;
    private Double adaptCoefficient;

    public UserActivityMenu(String menuId, String description, Double adaptCoefficient) {
        this.menuId = menuId;
        this.description = description;
        this.adaptCoefficient = adaptCoefficient;

        this.childrenMenuList = new ArrayList<UserActivityMenu>();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserActivityMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(UserActivityMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<UserActivityMenu> getChildrenMenuList() {
        return childrenMenuList;
    }

    public void setChildrenMenuList(List<UserActivityMenu> childrenMenuList) {
        this.childrenMenuList = childrenMenuList;
    }

    public Double getAdaptCoefficient() {
        return adaptCoefficient;
    }

    public void setAdaptCoefficient(Double adaptCoefficient) {
        this.adaptCoefficient = adaptCoefficient;
    }

    public UserActivityMenu addMenu(UserActivityMenu menu) {
        getChildrenMenuList().add(menu);
        menu.setParentMenu(this);
        return menu;
    }

    public UserActivityMenu removeMenu(UserActivityMenu menu) {
        getChildrenMenuList().remove(menu);
        menu.setParentMenu(null);
        return menu;
    }
}
