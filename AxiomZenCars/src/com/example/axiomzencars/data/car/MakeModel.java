package com.example.axiomzencars.data.car;

import android.os.Parcel;
import android.os.Parcelable;

public class MakeModel implements Parcelable {

    private String make;
    private String model;

    public MakeModel(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "MakeModel [make=" + make + ", model=" + model + "]";
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
        MakeModel other = (MakeModel) obj;
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
        dest.writeString(make);
        dest.writeString(model);
    }

    public static final Parcelable.Creator<MakeModel> CREATOR = new Parcelable.Creator<MakeModel>() {
        @Override
        public MakeModel createFromParcel(Parcel source) {
            String make = source.readString();
            String model = source.readString();
            return new MakeModel(make, model);
        }

        @Override
        public MakeModel[] newArray(int size) {
            return new MakeModel[size];
        }
    };
}
