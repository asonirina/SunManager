package com.sun.manager.dto;

import java.util.Date;

public class BaseSolariumData {
    protected Long dataId;
    protected Date startDate;
    protected Long minutes;
    protected Long totalPrice;
    protected String abonementNumber;

    public BaseSolariumData() {

    }

    public BaseSolariumData(Long dataId, Date startDate, Long minutes, Long totalPrice, String abonementNumber) {
        this.dataId = dataId;
        this.startDate = startDate;
        this.minutes = minutes;
        this.totalPrice = totalPrice;
        this.abonementNumber = abonementNumber;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAbonementNumber() {
        return abonementNumber;
    }

    public void setAbonementNumber(String abonementNumber) {
        this.abonementNumber = abonementNumber;
    }

    protected String res;

    public void generateRes() {
        String s = getAbonementNumber() == null ? "$ " + getTotalPrice() : getAbonementNumber();
        res = getMinutes() + " " + s;
    }

    public String getRes() {
        return res;
    }

}
