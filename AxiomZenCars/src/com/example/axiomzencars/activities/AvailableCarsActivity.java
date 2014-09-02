package com.example.axiomzencars.activities;

import com.example.axiomzencars.R;
import com.example.axiomzencars.adapter.CarArrayAdapter;
import com.example.axiomzencars.data.api.ApiRequest;
import com.example.axiomzencars.data.api.ApiRequest.OnTaskCompletedListener;
import com.example.axiomzencars.data.api.ApiResponse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AvailableCarsActivity extends Activity {

    private ListView availableCarsListView;
    private ListAdapter availableCarsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cars);

        ApiRequest.requestAvailableCars(new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                availableCarsListAdapter = new CarArrayAdapter(AvailableCarsActivity.this, response.getAvailableCars());

                availableCarsListView = (ListView) findViewById(R.id.available_cars_list_view);
                availableCarsListView.setAdapter(availableCarsListAdapter);
            }
        });
    }
}
