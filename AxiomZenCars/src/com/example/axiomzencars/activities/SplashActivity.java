package com.example.axiomzencars.activities;

import java.util.concurrent.TimeUnit;

import com.example.axiomzencars.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private static final long SPLASH_DURATION_MS = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, AvailableCarsActivity.class));
                finish();
            }
        }, SPLASH_DURATION_MS);
    }
}
