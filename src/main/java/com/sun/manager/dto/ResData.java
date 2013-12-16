package com.sun.manager.dto;

/**
 * User: iason
 */
public class ResData {
    private Long value;
    private final String name;

    private String res;

    public ResData(Long value, String name) {
        this.value = value;
        this.name = name;
        res = name  + ((value == null) ? "" : value);
    }

    public void setValue(Long value) {
        this.value = value;
        res = name  + ((value == null) ? "" : value);
    }

    public String getRes() {
        return res;
    }


}
