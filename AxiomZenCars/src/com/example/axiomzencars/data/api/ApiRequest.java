package com.example.axiomzencars.data.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class ApiRequest extends AsyncTask<Void, String, String> {

    public static interface OnTaskCompletedListener {
        public void onTaskCompleted(ApiResponse response);
    }

    private static final String TAG = ApiRequest.class.getSimpleName();
    private static final String API_ENDPOINT = "https://az-hack.s3.amazonaws.com/CarFinder/";
    private static final String API_AVAILABLE_CARS_PATH = "available_cars";

    // private static final String API_STANDARD_PRICE_PATH = "price/<make>/<model>/<year>";
    // private static final String API_BEST_CARS_FOR_YEAR_PATH = "best/<year>";
    // private static final String API_WORST_CARS_FOR_YEAR_PATH = "worst/<year>";

    public static void requestAvailableCars(OnTaskCompletedListener listener) {
        new ApiRequest(RequestType.AVAILABLE_CARS, String.format("%s%s", API_ENDPOINT, API_AVAILABLE_CARS_PATH), listener).execute();
    }

    // public static void requestStandardPrice(Car car, OnTaskCompletedListener listener) {
    // String apiRequestPath = API_STANDARD_PRICE_PATH.replace("<make>", car.getMake().getName())
    // .replace("<model>", car.getModel().getName()).replace("<year>",
    // String.valueOf(car.getYear().value()));
    // new ApiRequest(RequestType.STANDARD_PRICE, listener).execute(String.format("%s%s",
    // API_ENDPOINT, apiRequestPath));
    // }

    private RequestType requestType;
    private String url;
    private OnTaskCompletedListener listener;

    private ApiRequest(RequestType requestType, String url, OnTaskCompletedListener listener) {
        this.requestType = requestType;
        this.url = url;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {

        BufferedReader in = null;
        String result = null;

        try {

            URL yahoo = new URL(url);
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
        if (listener != null) {
            listener.onTaskCompleted(new ApiResponse().process(requestType, content));
        }
    }
}
