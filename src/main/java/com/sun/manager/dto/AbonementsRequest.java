package com.sun.manager.dto;

/**
 * User: iason
 * Date: 30.01.14
 */
public class AbonementsRequest {
    private String letter;
    private Long code;
    private String name;
    private String phone;

    private String res;


    public AbonementsRequest(String letter, Long code, String name, String phone) {
        this.letter = letter;
        this.code = code;
        this.name = name;
        this.phone = phone;
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

    public AbonementsRequest clone() {
        return new AbonementsRequest(letter, code, name, phone);
    }
}