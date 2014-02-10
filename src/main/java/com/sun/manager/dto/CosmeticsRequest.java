package com.sun.manager.dto;

import java.util.Date;

/**
 * User: iason
 * Date: 29.01.14
 */
public class CosmeticsRequest {
    private Cosmetics cosmetics;
    private Long count;

    private String res;

    private Date startDate;

    public CosmeticsRequest(Long count, Cosmetics cosmetics) {
        this.count = count;
        this.cosmetics = cosmetics;
        generateRes();
    }

    public CosmeticsRequest(Long count, Cosmetics cosmetics,Date startDate) {
        this.count = count;
        this.cosmetics = cosmetics;
        this.startDate = startDate;
        generateRes();
    }

    private void generateRes() {
        if (cosmetics == null && count == null) {
            res = "";
        } else {
            res = cosmetics.getName() + " - " + count;
        }
    }

    public Cosmetics getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Cosmetics cosmetics) {
        this.cosmetics = cosmetics;
        generateRes();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
        generateRes();
    }

    @Override
    public String toString() {
        if (cosmetics == null && count == null) {
            return "";
        }
        return cosmetics.getName() + " : " + count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CosmeticsRequest)) return false;

        CosmeticsRequest that = (CosmeticsRequest) o;

        if (cosmetics != null ? !cosmetics.equals(that.cosmetics) : that.cosmetics != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cosmetics != null ? cosmetics.hashCode() : 0;
    }

    public CosmeticsRequest clone() {
        return new CosmeticsRequest(count, cosmetics);
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
