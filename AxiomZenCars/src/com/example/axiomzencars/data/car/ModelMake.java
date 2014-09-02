package com.example.axiomzencars.data.car;

public class ModelMake {

    private String model;
    private String make;

    public ModelMake(String model, String make) {
        this.model = model;
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    @Override
    public String toString() {
        return "ModelMake [model=" + model + ", make=" + make + "]";
    }
}
