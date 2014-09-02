package com.example.axiomzencars.activities;

import com.example.axiomzencars.R;
import com.example.axiomzencars.data.ImageDownloader;
import com.example.axiomzencars.data.api.ApiRequest;
import com.example.axiomzencars.data.api.ApiRequest.OnTaskCompletedListener;
import com.example.axiomzencars.data.api.ApiResponse;
import com.example.axiomzencars.data.car.Car;
import com.example.axiomzencars.data.car.MakeModel;
import com.example.axiomzencars.data.car.Price;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailedCarActivity extends Activity {

    private static final String TAG = DetailedCarActivity.class.getSimpleName();

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_car);

        car = getIntent().getParcelableExtra(AvailableCarsActivity.CAR_EXTRA);

        if (car == null) {
            Toast.makeText(this, "Error loading car details", Toast.LENGTH_SHORT).show();
            finish();
        }

        Log.d(TAG, car.toString());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(String.format("%s %s", car.getModelMake().getMake(), car.getModelMake().getModel()));

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

        downloadCarImage(findViewById(R.id.car_image_progress_indicator), (ImageView) findViewById(R.id.car_image_view));

        downloadStandardPrice(findViewById(R.id.car_standard_price_progress_indicator),
                (TextView) findViewById(R.id.car_standard_price_text_view));

        downloadBestAndWorstOfYear(findViewById(R.id.car_best_or_worst_of_year_progress_indicator),
                (TextView) findViewById(R.id.car_best_or_worst_of_year_text_view));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }

    private void downloadCarImage(final View progressIndicator, final ImageView imageView) {
        new ImageDownloader(car.getImage().getUrl(), new ImageDownloader.OnImageDownloadedListener() {
            @Override
            public void onImageDownloaded(Bitmap bitmap) {
                progressIndicator.setVisibility(View.GONE);
                imageView.setImageBitmap(bitmap);
            }
        }).execute();
    }

    private void downloadStandardPrice(final View progressIndicator, final TextView textView) {
        ApiRequest.requestStandardPrice(car, new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                Price standardPrice = response.getStandardPrice();
                if (standardPrice != null) {
                    progressIndicator.setVisibility(View.GONE);
                    textView.setText(String.valueOf(standardPrice.value()));
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void downloadBestAndWorstOfYear(final View progressIndicator, final TextView textView) {
        ApiRequest.requestBestCarsForYear(car.getYear().value(), new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                if (response.getBestCarsForYear() != null) {
                    for (MakeModel modelMake : response.getBestCarsForYear()) {
                        if (car.isModelMake(modelMake)) {
                            textView.setText(String.format("Best of %d", car.getYear().value()));
                            textView.setTextColor(Color.GREEN);
                            textView.setVisibility(View.VISIBLE);
                            progressIndicator.setVisibility(View.GONE);
                            return;
                        }
                    }
                }
                ApiRequest.requestWorstCarsForYear(car.getYear().value(), new OnTaskCompletedListener() {
                    @Override
                    public void onTaskCompleted(ApiResponse response) {
                        if (response.getWorstCarsForYear() != null) {
                            for (MakeModel modelMake : response.getWorstCarsForYear()) {
                                if (car.isModelMake(modelMake)) {
                                    textView.setText(String.format("Worst of %d", car.getYear().value()));
                                    textView.setTextColor(Color.RED);
                                    textView.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        progressIndicator.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}
