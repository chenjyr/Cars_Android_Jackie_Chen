package com.example.axiomzencars.data.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.example.axiomzencars.data.car.Car;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ApiRequest extends AsyncTask<Void, String, String> {

    public static interface OnTaskCompletedListener {
        public void onTaskCompleted(ApiResponse response);
    }

    private static final String TAG = ApiRequest.class.getSimpleName();

    private static final String API_ENDPOINT = "https://az-hack.s3.amazonaws.com/CarFinder/";
    private static final String API_AVAILABLE_CARS_PATH = "available_cars";
    private static final String API_STANDARD_PRICE_PATH = "price/<make>/<model>/<year>";
    private static final String API_BEST_CARS_FOR_YEAR_PATH = "best/<year>";
    private static final String API_WORST_CARS_FOR_YEAR_PATH = "worst/<year>";

    public static void requestAvailableCars(Context context, OnTaskCompletedListener listener) {
        new ApiRequest(context, RequestType.AVAILABLE_CARS, String.format("%s%s", API_ENDPOINT, API_AVAILABLE_CARS_PATH), listener)
                .execute();
    }

    public static void requestStandardPrice(Context context, Car car, OnTaskCompletedListener listener) {
        String apiRequestPath = API_STANDARD_PRICE_PATH.replace("<make>", car.getModelMake().getMake())
                .replace("<model>", car.getModelMake().getModel()).replace("<year>", String.valueOf(car.getYear().value()));
        new ApiRequest(context, RequestType.STANDARD_PRICE, String.format("%s%s", API_ENDPOINT, apiRequestPath), listener).execute();
    }

    public static void requestBestCarsForYear(Context context, int year, OnTaskCompletedListener listener) {
        String apiRequestPath = API_BEST_CARS_FOR_YEAR_PATH.replace("<year>", String.valueOf(year));
        new ApiRequest(context, RequestType.BEST_CARS_FOR_YEAR, String.format("%s%s", API_ENDPOINT, apiRequestPath), listener).execute();
    }

    public static void requestWorstCarsForYear(Context context, int year, OnTaskCompletedListener listener) {
        String apiRequestPath = API_WORST_CARS_FOR_YEAR_PATH.replace("<year>", String.valueOf(year));
        new ApiRequest(context, RequestType.WORST_CARS_FOR_YEAR, String.format("%s%s", API_ENDPOINT, apiRequestPath), listener).execute();
    }

    private Context context;
    private RequestType requestType;
    private String url;
    private OnTaskCompletedListener listener;

    private ApiRequest(Context context, RequestType requestType, String url, OnTaskCompletedListener listener) {
        this.context = context;
        this.requestType = requestType;
        this.url = url;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d(TAG, "Api Request: " + url);
        BufferedReader in = null;
        String result = null;
        try {
            in = new BufferedReader(new InputStreamReader(new URL(url.replace(" ", "%20")).openStream()));

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
        if (content == null) {
            Toast.makeText(context, "Error retrieving data", Toast.LENGTH_SHORT).show();
        } else if (listener != null) {
            listener.onTaskCompleted(new ApiResponse().process(requestType, content));
        }
    }
}
