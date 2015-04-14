package com.sun.manager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuData implements Serializable {
    private String menuId;
    private String description;
    private MenuData parentMenu;
    private List<MenuData> childrenMenuList;

    public MenuData(String menuId, String description) {
        this.childrenMenuList = new ArrayList<MenuData>();
        this.menuId = menuId;
        this.description = description;
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

    public void setDescription(String name) {
        this.description = name;
    }

    public MenuData getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuData menu) {
        this.parentMenu = menu;
    }

    public List<MenuData> getChildrenMenuList() {
        return childrenMenuList;
    }

    public void setChildrenMenuList(List<MenuData> menuList) {
        this.childrenMenuList = menuList;
    }

    public MenuData addMenu(MenuData menu) {
        getChildrenMenuList().add(menu);
        menu.setParentMenu(this);
        return menu;
    }

    public MenuData removeMenu(MenuData menu) {
        getChildrenMenuList().remove(menu);
        menu.setParentMenu(null);
        return menu;
    }
}
