package com.dafelo.co.casona.order_detail.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/11/16.
 */

public class Order {
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("orders")
    @Expose
    private List<OrderItem> orders;
    @SerializedName("waitressName")
    @Expose
    private String waitressName;

    public Order() {
        this.total = 0;
        orders = new ArrayList<>();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    // this must change when the login is implemented
    public String getWaitressName() {
        return waitressName;
    }

    public void setWaitressName(String waitressName) {
        this.waitressName = waitressName;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
