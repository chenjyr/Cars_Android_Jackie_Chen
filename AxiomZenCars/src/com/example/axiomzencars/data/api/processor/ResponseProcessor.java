package com.example.axiomzencars.data.api.processor;

import org.json.JSONObject;

import com.example.axiomzencars.data.api.RequestType;

import android.util.Log;

public abstract class ResponseProcessor {

    private static final String TAG = ResponseProcessor.class.getSimpleName();

    public static Object process(RequestType requestType, String content) {
        switch (requestType) {
            case AVAILABLE_CARS:
                return new AvailableCarsProcessor(content).process();
            case STANDARD_PRICE:
                return new StandardPriceProcessor(content).process();
            case BEST_CARS_FOR_YEAR:
                return new BestCarsForYearProcessor(content).process();
            case WORST_CARS_FOR_YEAR:
                return new WorstCarsForYearProcessor(content).process();
            default:
                return new Object();
        }
    }

    private String content;

    public ResponseProcessor(String content) {
        this.content = content;
    }

    protected abstract Object process();

    protected String getContent() {
        return content;
    }

    protected String getStringValue(JSONObject jsonObject, String key) {
        String result = "";
        try {
            result = jsonObject.getString(key);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }
}
