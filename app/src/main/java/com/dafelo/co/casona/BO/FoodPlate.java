package com.dafelo.co.casona.BO;

/**
 * Created by root on 19/11/16.
 */

public class FoodPlate {
    private String name;
    private int price;

    public FoodPlate(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
