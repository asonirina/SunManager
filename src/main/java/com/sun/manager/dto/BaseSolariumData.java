package com.sun.manager.dto;

import java.util.Date;

public class BaseSolariumData implements Cloneable {
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
        generateRes();
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
        if (minutes == null && totalPrice == null && abonementNumber == null) {
            res = "";
            return;
        }
        String s = abonementNumber == null ? "$ " + totalPrice : abonementNumber;
        res = minutes + " : " + s;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public BaseSolariumData clone() {
        return new BaseSolariumData(dataId, startDate, minutes, totalPrice, abonementNumber);
    }
}
