package com.example.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {
    String name;
    String description;
    String imageUrl;
    int price;
    String category;

    public MenuItem(String name, String description, String imageUrl, int price, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
