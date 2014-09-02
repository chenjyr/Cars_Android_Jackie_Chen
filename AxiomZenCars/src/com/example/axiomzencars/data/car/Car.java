package com.example.axiomzencars.data.car;

public class Car {

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
}
