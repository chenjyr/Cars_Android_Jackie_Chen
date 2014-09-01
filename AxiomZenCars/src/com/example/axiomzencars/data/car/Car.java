package com.example.axiomzencars.data.car;

public class Car {

    private Make make;
    private Model model;
    private Year year;
    private Price price;
    private Description description;
    private Image image;
    private RankingCategory rankingCategory;

    public Car(Make make, Model model, Year year, Price price, Description description, Image image) {
        this(make, model, year, price, description, image, RankingCategory.UNKNOWN);
    }

    public Car(Make make, Model model, Year year, Price price, Description description, Image image, RankingCategory rankingCategory) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.description = description;
        this.image = image;
        this.rankingCategory = rankingCategory;
    }

    public Make getMake() {
        return make;
    }

    public Model getModel() {
        return model;
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

    public RankingCategory getRankingCategory() {
        return rankingCategory;
    }

    public void setRankingCategory(RankingCategory rankingCategory) {
        this.rankingCategory = rankingCategory;
    }

    @Override
    public String toString() {
        return "Car [make=" + make + ", model=" + model + ", year=" + year + ", price=" + price + ", description=" + description
                + ", image=" + image + ", rankingCategory=" + rankingCategory + "]";
    }

}
