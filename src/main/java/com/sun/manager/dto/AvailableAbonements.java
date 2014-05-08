package com.sun.manager.dto;

public class AvailableAbonements {
    private String letter;
    private Integer price;
    private Integer minutes;
    private Integer duration;

    public AvailableAbonements(String letter, Integer price, Integer minutes, Integer duration) {
        this.letter = letter;
        this.price = price;
        this.minutes = minutes;
        this.duration = duration;
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

    public Integer getDuration() {
        return duration;
    }
}
