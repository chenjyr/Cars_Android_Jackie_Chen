package com.example.axiomzencars.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.Description;
import com.example.axiomzencars.data.car.Image;
import com.example.axiomzencars.data.car.Make;
import com.example.axiomzencars.data.car.Model;
import com.example.axiomzencars.data.car.Price;
import com.example.axiomzencars.data.car.Year;

public class ApiRequest extends AsyncTask<String, String, String> {

    public static interface OnTaskCompletedListener {
        public void onTaskCompleted(ApiResponse response);
    }

    private static final String TAG = ApiRequest.class.getSimpleName();
    private static final String CAR_MAKE_KEY = "make";
    private static final String CAR_MODEL_KEY = "model";
    private static final String CAR_YEAR_KEY = "year";
    private static final String CAR_PRICE_KEY = "price";
    private static final String CAR_DESCRIPTION_KEY = "description";
    private static final String CAR_IMAGE_URL_KEY = "image";

    private static final String API_ENDPOINT = "https://az-hack.s3.amazonaws.com/CarFinder/";
    private static final String API_AVAILABLE_CARS_PATH = "available_cars";

    // private static final String API_STANDARD_PRICE_PATH = "price/<make>/<model>/<year>";
    // private static final String API_BEST_CARS_FOR_YEAR_PATH = "best/<year>";
    // private static final String API_WORST_CARS_FOR_YEAR_PATH = "worst/<year>";

    public static void requestAvailableCars(OnTaskCompletedListener listener) {
        new ApiRequest(RequestType.AVAILABLE_CARS, listener).execute(String.format("%s%s", API_ENDPOINT, API_AVAILABLE_CARS_PATH));
    }

    // public static void requestStandardPrice(Car car, OnTaskCompletedListener listener) {
    // String apiRequestPath = API_STANDARD_PRICE_PATH.replace("<make>", car.getMake().getName())
    // .replace("<model>", car.getModel().getName()).replace("<year>",
    // String.valueOf(car.getYear().value()));
    // new ApiRequest(RequestType.STANDARD_PRICE, listener).execute(String.format("%s%s",
    // API_ENDPOINT, apiRequestPath));
    // }

    private enum RequestType {
        AVAILABLE_CARS, STANDARD_PRICE, BEST_CARS_FOR_YEAR, WORST_CARS_FOR_YEAR;
    }

    private RequestType requestType;
    private OnTaskCompletedListener listener;

    private ApiRequest(RequestType requestType, OnTaskCompletedListener listener) {
        this.requestType = requestType;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader in = null;
        String result = null;

        try {

            URL yahoo = new URL(params[0]);
            in = new BufferedReader(new InputStreamReader(yahoo.openStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String content) {
        ApiResponse response = new ApiResponse();
        switch (requestType) {
            case AVAILABLE_CARS:
                response.setAvailableCars(getAvailableCars(content));
                break;
            case BEST_CARS_FOR_YEAR:
                break;
            case STANDARD_PRICE:
                break;
            case WORST_CARS_FOR_YEAR:
                break;
            default:
        }
        if (listener != null) {
            listener.onTaskCompleted(response);
        }
    }

    private List<Car> getAvailableCars(String content) {
        List<Car> result = new ArrayList<Car>();
        try {
            JSONArray jsonCarsArray = new JSONArray(content);
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

    private String getStringValue(JSONObject jsonObject, String key) {
        String result = "";
        try {
            result = jsonObject.getString(key);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }
}
