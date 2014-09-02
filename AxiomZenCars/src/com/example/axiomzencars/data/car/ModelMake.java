package com.example.axiomzencars.data.car;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelMake implements Parcelable {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((make == null) ? 0 : make.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ModelMake other = (ModelMake) obj;
        if (make == null) {
            if (other.make != null) return false;
        } else if (!make.equals(other.make)) return false;
        if (model == null) {
            if (other.model != null) return false;
        } else if (!model.equals(other.model)) return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(model);
        dest.writeString(make);
    }

    public static final Parcelable.Creator<ModelMake> CREATOR = new Parcelable.Creator<ModelMake>() {
        @Override
        public ModelMake createFromParcel(Parcel source) {
            String model = source.readString();
            String make = source.readString();
            return new ModelMake(model, make);
        }

        @Override
        public ModelMake[] newArray(int size) {
            return new ModelMake[size];
        }
    };
}
