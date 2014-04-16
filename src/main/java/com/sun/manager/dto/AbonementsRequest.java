package com.sun.manager.dto;

import java.util.Date;

/**
 * User: iason
 * Date: 30.01.14
 */
public class AbonementsRequest {
    private String letter = null;
    private Long code = null;
    private String name = null;
    private String phone;
    private String res = "";
    private Long price = null;
    private Date startDate = null;

    public AbonementsRequest() {

    }

    public AbonementsRequest(String letter, Long code, String name, String phone, Long price) {
        this.letter = letter;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.price = price;
        generateRes();
    }

    public AbonementsRequest(String letter, Long code, String name, String phone, Long price, Date startDate) {
        this.letter = letter;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.startDate = startDate;
        generateRes();
    }

    private void generateRes() {
        if (letter == null && code == null) {
            res = "";
            return;
        }

        res = letter + code;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRes() {
        return res;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getPrice() {
        return price;
    }

    public AbonementsRequest clone() {
        return new AbonementsRequest(letter, code, name, phone, price);
    }
}
