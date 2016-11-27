package com.dafelo.co.casona.order_detail.data.net;

import com.dafelo.co.casona.order_detail.data.entity.Order;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by root on 26/11/16.
 */

public interface OrderService {

    @POST("orders")
    Observable<Order> saveOrder(@Body Order order);
}
