package com.sun.manager.dto.menu;

public class ActivityInfo {
    String menuID;
    String parentMenuID;
    String description;
    Integer depth;
    Integer usageCount;
    Double adaptCoefficient;

    public ActivityInfo(String menuID, String parentMenuID, String description, Integer depth, Integer usageCount, Double adaptCoefficient) {
        this.menuID = menuID;
        this.parentMenuID = parentMenuID;
        this.description = description;
        this.depth = depth;
        this.usageCount = usageCount;
        this.adaptCoefficient = adaptCoefficient;
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

    public Double getAdaptCoefficient() {
        return adaptCoefficient;
    }

    public void setAdaptCoefficient(Double adaptCoefficient) {
        this.adaptCoefficient = adaptCoefficient;
    }
}
