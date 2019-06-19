package com.example.listview_exercise.model;

public class Product {
    String name;
    String url;
    int units;
    boolean isBought;

    public Product(String name, int units, boolean isBought, String url) {
        this.name = name;
        this.units = units;
        this.isBought = isBought;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setIsBought(boolean is_bought) {
        this.isBought = is_bought;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
