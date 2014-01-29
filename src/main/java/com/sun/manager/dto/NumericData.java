package com.sun.manager.dto;

import javafx.beans.property.SimpleStringProperty;

/**
 * User: iason
 */
public class NumericData {
    private final String data;

    public NumericData(int data) {
        this.data = String.valueOf(data);
    }

    public String getData() {
        return data;
    }

    public String toString() {
        return  data;
    }

}
