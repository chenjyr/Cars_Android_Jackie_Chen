package com.example.axiomzencars.data;

import java.util.ArrayList;
import java.util.List;

import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.Price;
import com.example.axiomzencars.data.car.RankingCategory;

public class ApiRequest {

    public static List<Car> getAvailableCars() {
        return new ArrayList<Car>();
    }

    public static Price getStandardPrice(Car car) {
        return new Price("12.34");
    }

    public static RankingCategory getRankingCategory(Car car) {
        return RankingCategory.NONE;
    }

    private ApiRequest() {}
}
