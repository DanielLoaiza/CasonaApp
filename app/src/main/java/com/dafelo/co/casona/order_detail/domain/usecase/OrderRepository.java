package com.dafelo.co.casona.order_detail.domain.usecase;

import com.dafelo.co.casona.order_detail.data.entity.Order;

import java.util.List;

import rx.Observable;

/**
 * Created by root on 26/11/16.
 */

public interface OrderRepository {
    Observable<List<Order>> getOrders();
    Observable<Order> saveOrder(Order order);
}
