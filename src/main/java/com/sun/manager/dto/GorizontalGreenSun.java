package com.sun.manager.dto;

import java.util.Date;

public class GorizontalGreenSun {

    private Long id;
    private Date startDate;
    private Long minuteCounbt;
    private Long totalPrice;
    private Long l2;
    private Long oneMinutePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getMinuteCounbt() {
        return minuteCounbt;
    }

    public void setMinuteCounbt(Long minuteCounbt) {
        this.minuteCounbt = minuteCounbt;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getL2() {
        return l2;
    }

    public void setL2(Long l2) {
        this.l2 = l2;
    }

    public Long getOneMinutePrice() {
        return oneMinutePrice;
    }

    public void setOneMinutePrice(Long oneMinutePrice) {
        this.oneMinutePrice = oneMinutePrice;
    }
}
