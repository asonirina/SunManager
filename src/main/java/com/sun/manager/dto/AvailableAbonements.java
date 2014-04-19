package com.sun.manager.dto;

public class AvailableAbonements {
    private String letter;
    private Integer price;
    private Integer minutes;

    public AvailableAbonements(String letter, Integer price, Integer minutes) {
        this.letter = letter;
        this.price = price;
        this.minutes = minutes;
    }

    public String getLetter() {
        return letter;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getMinutes() {
        return minutes;
    }
}
