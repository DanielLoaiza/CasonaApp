package com.dafelo.co.casona.order_detail.data.repository;

import com.dafelo.co.casona.order_detail.data.datasource.MenuDataStoreFactory;
import com.dafelo.co.casona.order_detail.data.datasource.OrderDataStoreFactory;
import com.dafelo.co.casona.order_detail.data.entity.Order;
import com.dafelo.co.casona.order_detail.domain.usecase.MenuRepository;
import com.dafelo.co.casona.order_detail.domain.usecase.OrderRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by root on 26/11/16.
 */

public class OrderDataRepository implements OrderRepository {

    private final OrderDataStoreFactory orderDataStoreFactory;

    /**
     * Constructs a {@link OrderRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     */
    @Inject
    public OrderDataRepository(OrderDataStoreFactory dataStoreFactory) {
        this.orderDataStoreFactory = dataStoreFactory;
    }

    @Override
    public Observable<List<Order>> getOrders() {
        return null;
    }

    @Override
    public Observable<Order> saveOrder(Order order) {
        return orderDataStoreFactory.createCloudDataStore().saveOrder(order);
    }
}
