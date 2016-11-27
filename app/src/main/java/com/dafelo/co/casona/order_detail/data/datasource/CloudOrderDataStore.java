package com.dafelo.co.casona.order_detail.data.datasource;

import com.dafelo.co.casona.order_detail.data.entity.Order;
import com.dafelo.co.casona.order_detail.data.entity.Sections;
import com.dafelo.co.casona.order_detail.data.net.MenuService;
import com.dafelo.co.casona.order_detail.data.net.OrderService;

import rx.Observable;

/**
 * Created by root on 25/11/16.
 */

public class CloudOrderDataStore implements OrderDataStore {

    private final OrderService orderService;

    /**
     * Construct a {@link OrderDataStore} based on connections to the api (Cloud).
     */
    CloudOrderDataStore(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Observable<Order> saveOrder(Order order) {
        return orderService.saveOrder(order);
    }
}
