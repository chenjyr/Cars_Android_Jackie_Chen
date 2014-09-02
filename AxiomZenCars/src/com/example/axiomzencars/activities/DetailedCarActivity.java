package com.example.axiomzencars.activities;

import com.example.axiomzencars.R;
import com.example.axiomzencars.data.ImageDownloader;
import com.example.axiomzencars.data.api.ApiRequest;
import com.example.axiomzencars.data.api.ApiRequest.OnTaskCompletedListener;
import com.example.axiomzencars.data.api.ApiResponse;
import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.ModelMake;
import com.example.axiomzencars.data.car.Price;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedCarActivity extends Activity {

    private static final String TAG = DetailedCarActivity.class.getSimpleName();

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_car);

        car = getIntent().getParcelableExtra(AvailableCarsActivity.CAR_EXTRA);

        Log.d(TAG, car.toString());

        // TODO : Loading View until standard price is retrieved

        TextView carMakeTextView = (TextView) findViewById(R.id.car_make_text_view);
        TextView carModelTextView = (TextView) findViewById(R.id.car_model_text_view);
        TextView carYearTextView = (TextView) findViewById(R.id.car_year_text_view);
        TextView carPriceTextView = (TextView) findViewById(R.id.car_price_text_view);
        TextView carDescriptionTextView = (TextView) findViewById(R.id.car_description_text_view);

        carMakeTextView.setText(car.getModelMake().getMake());
        carModelTextView.setText(car.getModelMake().getModel());
        carYearTextView.setText(String.valueOf(car.getYear().value()));
        carPriceTextView.setText(String.valueOf(car.getPrice().value()));
        carDescriptionTextView.setText(car.getDescription().getDescriptionText());

        // TODO : Image loading view

        final ImageView carImageView = (ImageView) findViewById(R.id.car_image_view);

        new ImageDownloader(car.getImage().getUrl(), new ImageDownloader.OnImageDownloadedListener() {
            @Override
            public void onImageDownloaded(Bitmap bitmap) {
                carImageView.setImageBitmap(bitmap);
            }
        }).execute();

        final TextView carStandardPriceTextView = (TextView) findViewById(R.id.car_standard_price_text_view);

        ApiRequest.requestStandardPrice(car, new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                Price standardPrice = response.getStandardPrice();
                if (standardPrice != null) {
                    carStandardPriceTextView.setText(String.valueOf(standardPrice.value()));
                    carStandardPriceTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        final TextView carBestOrWorstOfYearTextView = (TextView) findViewById(R.id.car_best_or_worst_of_year_text_view);

        ApiRequest.requestBestCarsForYear(car.getYear().value(), new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                if (response.getBestCarsForYear() != null) {
                    for (ModelMake modelMake : response.getBestCarsForYear()) {
                        if (car.isModelMake(modelMake)) {
                            carBestOrWorstOfYearTextView.setText(String.format("Best of %d", car.getYear().value()));
                            carBestOrWorstOfYearTextView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        ApiRequest.requestWorstCarsForYear(car.getYear().value(), new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                if (response.getWorstCarsForYear() != null) {
                    for (ModelMake modelMake : response.getWorstCarsForYear()) {
                        if (car.isModelMake(modelMake)) {
                            carBestOrWorstOfYearTextView.setText(String.format("Worst of %d", car.getYear().value()));
                            carBestOrWorstOfYearTextView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
    }
}
