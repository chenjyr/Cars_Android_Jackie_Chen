package com.example.axiomzencars.data.car;

import android.os.Parcel;
import android.os.Parcelable;

public class Description implements Parcelable {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descriptionText == null) ? 0 : descriptionText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Description other = (Description) obj;
        if (descriptionText == null) {
            if (other.descriptionText != null) return false;
        } else if (!descriptionText.equals(other.descriptionText)) return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descriptionText);
    }

    public static final Parcelable.Creator<Description> CREATOR = new Parcelable.Creator<Description>() {
        @Override
        public Description createFromParcel(Parcel source) {
            String descriptionText = source.readString();
            return new Description(descriptionText);
        }

        @Override
        public Description[] newArray(int size) {
            return new Description[size];
        }
    };
}
