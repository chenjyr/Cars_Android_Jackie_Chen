package com.example.axiomzencars.activities;

import com.example.axiomzencars.R;

import com.example.axiomzencars.data.api.ApiRequest;
import com.example.axiomzencars.data.api.ApiResponse;
import com.example.axiomzencars.data.api.ApiRequest.OnTaskCompletedListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }

        ApiRequest.requestAvailableCars(new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                System.out.println("TESTJC: Available Cars - " + response);

                ApiRequest.requestStandardPrice(response.getAvailableCars().get(0), new OnTaskCompletedListener() {
                    @Override
                    public void onTaskCompleted(ApiResponse response) {
                        System.out.println("TESTJC: Standard Price - " + response);
                    }
                });
            }
        });

        ApiRequest.requestBestCarsForYear(2000, new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                System.out.println("TESTJC: Best Cars for 2009 - " + response);
            }
        });

        ApiRequest.requestWorstCarsForYear(2000, new OnTaskCompletedListener() {
            @Override
            public void onTaskCompleted(ApiResponse response) {
                System.out.println("TESTJC: Worst Cars for 2009 - " + response);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
