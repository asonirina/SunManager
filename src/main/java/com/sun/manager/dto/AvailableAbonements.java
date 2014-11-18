package com.sun.manager.dto;

public class AvailableAbonements {
    private String letter;
    private Integer price;
    private Integer minutes;
    private Integer duration;
    private Integer availableTime;

    public AvailableAbonements(String letter, Integer price, Integer minutes, Integer duration, Integer availableTime) {
        this.letter = letter;
        this.price = price;
        this.minutes = minutes;
        this.duration = duration;
        this.availableTime = availableTime;
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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(Integer availableTime) {
        this.availableTime = availableTime;
    }
}
