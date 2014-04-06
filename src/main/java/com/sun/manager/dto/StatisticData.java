package com.sun.manager.dto;

import java.util.Date;

public class StatisticData {

    private Integer id;
    private Date startDate;
    private Integer residue;
    private Integer bank;
    private Integer bookingPerDay;
    private Integer officialSalary;
    private Integer quenching;
    private Integer accumulation;
    private String userLogin;

    public StatisticData(Integer residue, Integer bank,
                         Integer bookingPerDay, Integer officialSalary,
                         Integer quenching, Integer accumulation) {
        this.residue = residue;
        this.bank = bank;
        this.bookingPerDay = bookingPerDay;
        this.officialSalary = officialSalary;
        this.quenching = quenching;
        this.accumulation = accumulation;

    }

    public StatisticData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    public Integer getBookingPerDay() {
        return bookingPerDay;
    }

    public void setBookingPerDay(Integer bookingPerDay) {
        this.bookingPerDay = bookingPerDay;
    }

    public Integer getOfficialSalary() {
        return officialSalary;
    }

    public void setOfficialSalary(Integer officialSalary) {
        this.officialSalary = officialSalary;
    }

    public Integer getQuenching() {
        return quenching;
    }

    public void setQuenching(Integer quenching) {
        this.quenching = quenching;
    }

    public Integer getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(Integer accumulation) {
        this.accumulation = accumulation;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
