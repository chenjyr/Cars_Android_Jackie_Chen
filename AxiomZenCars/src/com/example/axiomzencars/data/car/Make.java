package com.example.axiomzencars.data.car;

public class Make {

    private String name;

    public Make(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Make [name=" + name + "]";
    }

}
