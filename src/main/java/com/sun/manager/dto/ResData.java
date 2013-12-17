package com.sun.manager.dto;

import com.google.common.base.Joiner;

/**
 * User: iason
 */
public class ResData {
    private String array[] = new String[4];

    private String res;

    public ResData(String name) {
        array[0] = name;
        array[1] = "";
        array[2] = "";
        array[3] = "";
        generateRes();
    }

    public ResData(String name1, String name2) {
        array[0] = name1;
        array[1] = "";
        array[2] = name2;
        array[3] = "";
        generateRes();
    }

    public void setFirstValue(Long value) {
        array[1] = String.valueOf(value);
        generateRes();
    }

    public void setBothValues(Long value1, Long value2) {
        array[1] = String.valueOf(value1);
        array[3] = String.valueOf(value2);
        generateRes();
    }

    public String getRes() {
        return res;
    }

    private void generateRes() {
        res = Joiner.on(" ").join(array);
    }
}
