package com.example.axiomzencars.data.api.processor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.example.axiomzencars.data.car.MakeModel;

public class BestCarsForYearProcessor extends ResponseProcessor {

    private static final String TAG = BestCarsForYearProcessor.class.getSimpleName();

    public BestCarsForYearProcessor(String content) {
        super(content);
    }

    @Override
    protected List<MakeModel> process() {
        List<MakeModel> result = new ArrayList<MakeModel>();
        try {
            JSONArray jsonCarsArray = new JSONArray(getContent());
            for (int i = 0; i < jsonCarsArray.length(); i++) {
                String modelMake = jsonCarsArray.getString(i);
                String[] params = modelMake.split(" ", 2);
                result.add(new MakeModel(params[0], params[1]));
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }
}
