package com.example.axiomzencars.data.car;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {

    private ModelMake modelMake;
    private Year year;
    private Price price;
    private Description description;
    private Image image;
    private Boolean isBestCarForYear;
    private Boolean isWorstCarForYear;

    public Car(ModelMake modelMake, Year year, Price price, Description description, Image image) {
        this.modelMake = modelMake;
        this.year = year;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public ModelMake getModelMake() {
        return modelMake;
    }

    public Year getYear() {
        return year;
    }

    public Price getPrice() {
        return price;
    }

    public Description getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public Boolean getIsBestCarForYear() {
        return isBestCarForYear;
    }

    public void setIsBestCarForYear(Boolean isBestCarForYear) {
        this.isBestCarForYear = isBestCarForYear;
    }

    public Boolean getIsWorstCarForYear() {
        return isWorstCarForYear;
    }

    public void setIsWorstCarForYear(Boolean isWorstCarForYear) {
        this.isWorstCarForYear = isWorstCarForYear;
    }

    @Override
    public String toString() {
        return "Car [modelMake=" + modelMake + ", year=" + year + ", price=" + price + ", description=" + description + ", image=" + image
                + ", isBestCarForYear=" + isBestCarForYear + ", isWorstCarForYear=" + isWorstCarForYear + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((modelMake == null) ? 0 : modelMake.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Car other = (Car) obj;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (image == null) {
            if (other.image != null) return false;
        } else if (!image.equals(other.image)) return false;
        if (modelMake == null) {
            if (other.modelMake != null) return false;
        } else if (!modelMake.equals(other.modelMake)) return false;
        if (price == null) {
            if (other.price != null) return false;
        } else if (!price.equals(other.price)) return false;
        if (year == null) {
            if (other.year != null) return false;
        } else if (!year.equals(other.year)) return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(modelMake, flags);
        dest.writeParcelable(year, flags);
        dest.writeParcelable(price, flags);
        dest.writeParcelable(description, flags);
        dest.writeParcelable(image, flags);
        dest.writeValue(isBestCarForYear);
        dest.writeValue(isWorstCarForYear);
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel source) {
            ModelMake modelMake = source.readParcelable(ModelMake.class.getClassLoader());
            Year year = source.readParcelable(Year.class.getClassLoader());
            Price price = source.readParcelable(Price.class.getClassLoader());
            Description description = source.readParcelable(Description.class.getClassLoader());
            Image image = source.readParcelable(Image.class.getClassLoader());
            Boolean isBestCarForYear = (Boolean) source.readValue(Boolean.class.getClassLoader());
            Boolean isWorstCarForYear = (Boolean) source.readValue(Boolean.class.getClassLoader());

            Car car = new Car(modelMake, year, price, description, image);
            car.setIsBestCarForYear(isBestCarForYear);
            car.setIsWorstCarForYear(isWorstCarForYear);

            return car;
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
