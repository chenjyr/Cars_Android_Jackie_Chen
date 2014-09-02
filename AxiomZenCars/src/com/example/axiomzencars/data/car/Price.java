package com.example.axiomzencars.data.car;

public class Price {

    private int price;

    public Price(String price) {
        this(Integer.valueOf(price));
    }

    public Price(int price) {
        this.price = price;
    }

    public int value() {
        return price;
    }

    @Override
    public String toString() {
        return "Price [price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + price;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Price other = (Price) obj;
        if (price != other.price) return false;
        return true;
    }

}
