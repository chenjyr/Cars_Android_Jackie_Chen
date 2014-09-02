package com.example.axiomzencars.data.api.processor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.axiomzencars.data.car.ModelMake;

import android.util.Log;

public class WorstCarsForYearProcessor extends ResponseProcessor {

    private static final String TAG = WorstCarsForYearProcessor.class.getSimpleName();

    public WorstCarsForYearProcessor(String content) {
        super(content);
    }

    @Override
    protected List<ModelMake> process() {
        List<ModelMake> result = new ArrayList<ModelMake>();
        try {
            JSONArray jsonCarsArray = new JSONArray(getContent());
            for (int i = 0; i < jsonCarsArray.length(); i++) {
                String modelMake = jsonCarsArray.getString(0);
                String[] params = modelMake.split(" ");
                result.add(new ModelMake(params[0], params[1]));
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }
}
