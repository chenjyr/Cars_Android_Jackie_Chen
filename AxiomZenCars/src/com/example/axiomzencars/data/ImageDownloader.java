package com.example.axiomzencars.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageDownloader extends AsyncTask<Void, String, Bitmap> {

    private static final String TAG = ImageDownloader.class.getSimpleName();

    public static interface OnImageDownloadedListener {
        public void onImageDownloaded(Bitmap bitmap);
    }

    private String url;
    private OnImageDownloadedListener listener;

    public ImageDownloader(String url, OnImageDownloadedListener listener) {
        this.url = url;
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap result = null;
        try {
            result = BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
        } catch (MalformedURLException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (listener != null && bitmap != null) {
            listener.onImageDownloaded(bitmap);
        }
    }
}
