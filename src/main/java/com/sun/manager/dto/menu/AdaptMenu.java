package com.sun.manager.dto.menu;

public class AdaptMenu {
    String menuID;
    String parentMenuID;
    String description;
    Integer depth;
    Integer usageCount;

    public AdaptMenu(String menuID, String parentMenuID, String description, Integer depth, Integer usageCount) {
        this.menuID = menuID;
        this.parentMenuID = parentMenuID;
        this.description = description;
        this.depth = depth;
        this.usageCount = usageCount;
    }
    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getParentMenuID() {
        return parentMenuID;
    }

    public void setParentMenuID(String parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }
}
