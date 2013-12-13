package com.sun.manager.dto;

public class VerticalSunData extends BaseSolariumData {
    private String res;

    public void generateRes() {
        String s = getAbonementNumber() == null ? "$ " + getTotalPrice() : getAbonementNumber();
        res = getMinutes() + " " + s;
    }

    public String getRes() {
        return res;
    }

}
