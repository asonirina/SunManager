package com.sun.manager.dto;

/**
 * User: iason
 * Date: 29.01.14
 */
public class CosmeticsRequest {
    private Cosmetics cosmetics;
    private Long count;

    public CosmeticsRequest(Long count, Cosmetics cosmetics) {
        this.count = count;
        this.cosmetics = cosmetics;
    }

    public Cosmetics getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(Cosmetics cosmetics) {
        this.cosmetics = cosmetics;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return cosmetics.getName() + " : " + count;
    }
}
