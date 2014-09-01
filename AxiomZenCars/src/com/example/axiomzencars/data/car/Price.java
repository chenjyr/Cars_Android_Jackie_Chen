package com.example.axiomzencars.data.car;

import java.math.BigDecimal;

public class Price {

    private BigDecimal price;

    public Price(String price) {
        this(new BigDecimal(price));
    }

    public Price(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
