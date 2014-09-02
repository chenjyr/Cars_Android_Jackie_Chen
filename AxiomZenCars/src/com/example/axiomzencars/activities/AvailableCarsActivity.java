package com.example.axiomzencars.activities;

import com.example.axiomzencars.R;
import com.example.axiomzencars.adapter.CarArrayAdapter;
import com.example.axiomzencars.data.api.ApiRequest;
import com.example.axiomzencars.data.api.ApiRequest.OnTaskCompletedListener;
import com.example.axiomzencars.data.api.ApiResponse;
import com.example.axiomzencars.data.car.Car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AvailableCarsActivity extends Activity implements OnItemClickListener {

    public static final String CAR_EXTRA = "CAR_EXTRA";

    private ListView availableCarsListView;
    private ListAdapter availableCarsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cars);

        final View availableCarsProgressIndicator = findViewById(R.id.available_cars_progress_indicator);

        ApiRequest.requestAvailableCars(this, new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                availableCarsListAdapter = new CarArrayAdapter(AvailableCarsActivity.this, response.getAvailableCars());
                availableCarsListView = (ListView) findViewById(R.id.available_cars_list_view);
                availableCarsListView.setAdapter(availableCarsListAdapter);
                availableCarsListView.setOnItemClickListener(AvailableCarsActivity.this);
                availableCarsProgressIndicator.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Car car = (Car) availableCarsListAdapter.getItem(position);
        Intent intent = new Intent(this, DetailedCarActivity.class);
        intent.putExtra(CAR_EXTRA, car);
        startActivity(intent);
    }
}
