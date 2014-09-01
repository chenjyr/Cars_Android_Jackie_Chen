package com.example.axiomzencars.data;

import java.util.List;

import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.Price;
import com.example.axiomzencars.data.car.RankingCategory;

public class ApiResponse {

    private List<Car> availableCars;
    private Price standardPrice;
    private RankingCategory rankingCategory;

    public List<Car> getAvailableCars() {
        return availableCars;
    }

    public void setAvailableCars(List<Car> availableCars) {
        this.availableCars = availableCars;
    }

    public Price getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Price standardPrice) {
        this.standardPrice = standardPrice;
    }

    public RankingCategory getRankingCategory() {
        return rankingCategory;
    }

    public void setRankingCategory(RankingCategory rankingCategory) {
        this.rankingCategory = rankingCategory;
    }

    @Override
    public String toString() {
        return "ApiResponse [availableCars=" + availableCars + ", standardPrice=" + standardPrice + ", rankingCategory=" + rankingCategory
                + "]";
    }

}
