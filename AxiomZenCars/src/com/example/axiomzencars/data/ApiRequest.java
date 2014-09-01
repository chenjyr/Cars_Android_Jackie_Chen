package com.example.axiomzencars.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.Price;

public class ApiRequest extends AsyncTask<String, String, String> {

    private static final String TAG = ApiRequest.class.getSimpleName();

    public static interface OnTaskCompletedListener {
        public void onTaskCompleted(ApiResponse response);
    }

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
        return new ArrayList<Car>();
    }
}
