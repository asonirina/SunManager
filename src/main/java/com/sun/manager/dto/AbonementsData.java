package com.sun.manager.dto;

import java.util.Date;

public class AbonementsData {

    private String letter;
    private String phone;
    private Date buyDate;
    private Date endDate;
    private Long minutes;
    private String clientName;
    private Long code;

    public AbonementsData(String clientName, String letter, Long code, String phone, Long minutes, Date buyDate) {
        this.clientName = clientName;
        this.letter = letter;
        this.code = code;
        this.phone = phone;
        this.minutes = minutes;
        this.buyDate = buyDate;
        this.endDate = null;
    }

    public AbonementsData(String clientName, String phone) {
        this.clientName = clientName;
        this.phone = phone;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
