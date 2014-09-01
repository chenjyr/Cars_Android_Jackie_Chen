package com.example.axiomzencars.data.car;

public class Description {

    private String descriptionText;

    public Description(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    @Override
    public String toString() {
        return "Description [descriptionText=" + descriptionText + "]";
    }
}
