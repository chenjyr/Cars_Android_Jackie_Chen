package com.example.axiomzencars.data.api;

import java.util.List;

import com.example.axiomzencars.data.api.processor.ResponseProcessor;
import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.ModelMake;
import com.example.axiomzencars.data.car.Price;

public class ApiResponse {

    private List<Car> availableCars;
    private Price standardPrice;
    private List<ModelMake> bestCarsForYear;
    private List<ModelMake> worstCarsForYear;

    @SuppressWarnings("unchecked")
    public ApiResponse process(RequestType requestType, String content) {
        Object processedResponse = ResponseProcessor.process(requestType, content);
        switch (requestType) {
            case AVAILABLE_CARS:
                setAvailableCars((List<Car>) processedResponse);
                break;
            case STANDARD_PRICE:
                setStandardPrice((Price) processedResponse);
                break;
            case BEST_CARS_FOR_YEAR:
                setBestCarsForYear((List<ModelMake>) processedResponse);
                break;
            case WORST_CARS_FOR_YEAR:
                setWorstCarsForYear((List<ModelMake>) processedResponse);
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

    public List<ModelMake> getBestCarsForYear() {
        return bestCarsForYear;
    }

    public void setBestCarsForYear(List<ModelMake> bestCarsForYear) {
        this.bestCarsForYear = bestCarsForYear;
    }

    public List<ModelMake> getWorstCarsForYear() {
        return worstCarsForYear;
    }

    public void setWorstCarsForYear(List<ModelMake> worstCarsForYear) {
        this.worstCarsForYear = worstCarsForYear;
    }

    @Override
    public String toString() {
        return "ApiResponse [availableCars=" + availableCars + ", standardPrice=" + standardPrice + ", bestCarsForYear=" + bestCarsForYear
                + ", worstCarsForYear=" + worstCarsForYear + "]";
    }
}
