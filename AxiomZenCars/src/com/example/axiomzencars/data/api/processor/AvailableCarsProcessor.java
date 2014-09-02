package com.example.axiomzencars.data.api.processor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.Description;
import com.example.axiomzencars.data.car.Image;
import com.example.axiomzencars.data.car.Make;
import com.example.axiomzencars.data.car.Model;
import com.example.axiomzencars.data.car.Price;
import com.example.axiomzencars.data.car.Year;

public class AvailableCarsProcessor extends ResponseProcessor {

    private static final String TAG = AvailableCarsProcessor.class.getSimpleName();

    private static final String CAR_MAKE_KEY = "make";
    private static final String CAR_MODEL_KEY = "model";
    private static final String CAR_YEAR_KEY = "year";
    private static final String CAR_PRICE_KEY = "price";
    private static final String CAR_DESCRIPTION_KEY = "description";
    private static final String CAR_IMAGE_URL_KEY = "image";

    public AvailableCarsProcessor(String content) {
        super(content);
    }

    @Override
    public List<Car> process() {
        List<Car> result = new ArrayList<Car>();
        try {
            JSONArray jsonCarsArray = new JSONArray(getContent());
            for (int i = 0; i < jsonCarsArray.length(); i++) {
                JSONObject jsonCarObject = jsonCarsArray.getJSONObject(i);

                String make = getStringValue(jsonCarObject, CAR_MAKE_KEY);
                String model = getStringValue(jsonCarObject, CAR_MODEL_KEY);
                String year = getStringValue(jsonCarObject, CAR_YEAR_KEY);
                String price = getStringValue(jsonCarObject, CAR_PRICE_KEY);
                String description = getStringValue(jsonCarObject, CAR_DESCRIPTION_KEY);
                String imageUrl = getStringValue(jsonCarObject, CAR_IMAGE_URL_KEY);

                result.add(new Car(new Make(make), new Model(model), new Year(Integer.valueOf(year)), new Price(price), new Description(
                        description), new Image(imageUrl)));
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }
}
