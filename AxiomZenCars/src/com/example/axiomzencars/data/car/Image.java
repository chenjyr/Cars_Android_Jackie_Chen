package com.example.axiomzencars.data.car;

public class Image {

    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Image [url=" + url + "]";
    }

}
