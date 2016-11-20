package com.dafelo.co.casona.BO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/11/16.
 */

public class Order {
    private int total;
    private List<OrderItem> orders;

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

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
