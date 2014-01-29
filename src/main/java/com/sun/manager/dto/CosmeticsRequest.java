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
}
