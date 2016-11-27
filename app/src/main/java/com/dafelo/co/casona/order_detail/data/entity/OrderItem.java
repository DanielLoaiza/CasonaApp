package com.dafelo.co.casona.order_detail.data.entity;

import com.dafelo.co.casona.order_detail.data.entity.Food;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 19/11/16.
 */

public class OrderItem {
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("plate")
    @Expose
    private Food plate;

    public OrderItem(Food foodPlate) {
        this.quantity = 1;
        this.plate = foodPlate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food getPlate() {
        return plate;
    }

    public void setPlate(Food plate) {
        this.plate = plate;
    }

    public int getTotal() {
        return plate.getPrice() * quantity;
    }
}
