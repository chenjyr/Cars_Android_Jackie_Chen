package com.example.axiomzencars.data.api;

import java.util.List;

import com.example.axiomzencars.data.api.processor.AvailableCarsProcessor;
import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.Price;
import com.example.axiomzencars.data.car.RankingCategory;

public class ApiResponse {

    private List<Car> availableCars;
    private Price standardPrice;
    private RankingCategory rankingCategory;

    public ApiResponse process(RequestType requestType, String content) {
        switch (requestType) {
            case AVAILABLE_CARS:
                setAvailableCars(new AvailableCarsProcessor(content).process());
                break;
            case BEST_CARS_FOR_YEAR:
                break;
            case STANDARD_PRICE:
                break;
            case WORST_CARS_FOR_YEAR:
                break;
            default:
        }
        return this;
    }

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
