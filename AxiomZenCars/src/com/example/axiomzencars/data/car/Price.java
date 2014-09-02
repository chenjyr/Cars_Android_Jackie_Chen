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

}
