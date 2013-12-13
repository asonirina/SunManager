package com.sun.manager.dto;

import java.util.Date;

public class Abonements {

    private String abonementCode;
    private Long price;
    private Long customerId;
    private Date buyDate;
    private Date endDate;
    private Long minutes;
    private Long duration;
    private Boolean isFree;

    public String getAbonementCode() {
        return abonementCode;
    }

    public void setAbonementCode(String abonementCode) {
        this.abonementCode = abonementCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
