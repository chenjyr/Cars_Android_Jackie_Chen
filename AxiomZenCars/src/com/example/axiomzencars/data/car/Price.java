package com.example.axiomzencars.data.car;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {

    private int price;

    public Price(String price) {
        this(Integer.valueOf(price));
    }

    public Price(int price) {
        this.price = price;
    }

    public int value() {
        return price;
    }

    @Override
    public String toString() {
        return "Price [price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + price;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Price other = (Price) obj;
        if (price != other.price) return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            int price = source.readInt();
            return new Price(price);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
