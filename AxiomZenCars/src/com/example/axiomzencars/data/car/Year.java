package com.example.axiomzencars.data.car;

import android.os.Parcel;
import android.os.Parcelable;

public class Year implements Parcelable {

    private int year;

    public Year(int year) {
        this.year = year;
    }

    public int value() {
        return year;
    }

    @Override
    public String toString() {
        return "Year [year=" + year + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Year other = (Year) obj;
        if (year != other.year) return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(year);
    }

    public static final Parcelable.Creator<Year> CREATOR = new Parcelable.Creator<Year>() {
        @Override
        public Year createFromParcel(Parcel source) {
            int year = source.readInt();
            return new Year(year);
        }

        @Override
        public Year[] newArray(int size) {
            return new Year[size];
        }
    };
}
