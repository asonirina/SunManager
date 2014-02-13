package com.sun.manager.dto;

public class Cosmetics {

    private Long id;
    private String name;
    private Long price;
    private Long count;

    public Cosmetics() {
    }

    public Cosmetics(Long id, String name, Long price, Long count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return name + " : " +count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cosmetics)) return false;

        Cosmetics cosmetics = (Cosmetics) o;

        if (count != null ? !count.equals(cosmetics.count) : cosmetics.count != null) return false;
        if (id != null ? !id.equals(cosmetics.id) : cosmetics.id != null) return false;
        if (name != null ? !name.equals(cosmetics.name) : cosmetics.name != null) return false;
        if (price != null ? !price.equals(cosmetics.price) : cosmetics.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }
}

