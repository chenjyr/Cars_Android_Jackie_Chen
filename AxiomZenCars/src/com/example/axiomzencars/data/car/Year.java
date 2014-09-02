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
}
