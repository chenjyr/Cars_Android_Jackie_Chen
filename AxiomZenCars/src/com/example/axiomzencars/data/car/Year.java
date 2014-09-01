package com.example.axiomzencars.data.car;

public class Year {

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

}
