package com.example.axiomzencars.data.car;

public class Description {

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

}
