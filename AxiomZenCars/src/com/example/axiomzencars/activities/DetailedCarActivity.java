package com.example.axiomzencars.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.axiomzencars.R;
import com.example.axiomzencars.data.ImageDownloader;
import com.example.axiomzencars.data.car.Car;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
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
    }
}
