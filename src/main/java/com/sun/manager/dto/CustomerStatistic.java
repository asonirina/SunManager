package com.sun.manager.dto;

public class CustomerStatistic {
    private String name;
    private Long   abonementsCount;

    public CustomerStatistic(String name, Long abonementsCount) {
        this.name = name;
        this.abonementsCount = abonementsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAbonementsCount() {
        return abonementsCount;
    }

    public void setAbonementsCount(Long abonementsCount) {
        this.abonementsCount = abonementsCount;
    }
}
