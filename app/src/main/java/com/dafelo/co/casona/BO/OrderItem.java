package com.dafelo.co.casona.BO;

/**
 * Created by root on 19/11/16.
 */

public class OrderItem {
    private int quantity;
    private FoodPlate plate;

    public OrderItem(FoodPlate foodPlate) {
        this.quantity = 1;
        this.plate = foodPlate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public FoodPlate getPlate() {
        return plate;
    }

    public void setPlate(FoodPlate plate) {
        this.plate = plate;
    }

    public int getTotal() {
        return plate.getPrice() * quantity;
    }
}
