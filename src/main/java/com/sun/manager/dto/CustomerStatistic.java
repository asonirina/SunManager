package com.sun.manager.dto;

public class CustomerStatistic {
    private String phone;
    private Long abonementsCount;

    public CustomerStatistic(String phone, Long abonementsCount) {
        this.phone = phone;
        this.abonementsCount = abonementsCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAbonementsCount() {
        return abonementsCount;
    }

    public void setAbonementsCount(Long abonementsCount) {
        this.abonementsCount = abonementsCount;
    }
}
