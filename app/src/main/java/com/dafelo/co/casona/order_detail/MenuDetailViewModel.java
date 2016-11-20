package com.dafelo.co.casona.order_detail;

import android.util.Log;

import com.dafelo.co.casona.BO.FoodPlate;
import com.dafelo.co.casona.BO.Order;
import com.dafelo.co.casona.BO.OrderItem;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by root on 20/11/16.
 */

public class MenuDetailViewModel {

    private BehaviorSubject<Order> orderSubject;
    private Order order;

     MenuDetailViewModel() {
        order = new Order();
        orderSubject = BehaviorSubject.create(order);
    }


    void addOrder(FoodPlate plate) {
        OrderItem orderItem = new OrderItem(plate);
        order.getOrders().add(orderItem);
        int total = order.getTotal() + orderItem.getPlate().getPrice();
        order.setTotal(total);
        orderSubject.onNext(order);
    }

    Observable<Integer> itemQuantityChanged(OrderItem orderItem, int currentQuantity, int previousQuantity) {

        return Observable.
                from(order.getOrders())
                .observeOn(Schedulers.computation())
                .filter(orderItemValue -> orderItemValue.equals(orderItem))
                .map(order.getOrders()::indexOf)
                .doOnNext(index -> {
                    OrderItem orderItem1 = order.getOrders().get(index);
                    int platePrice = orderItem1.getPlate().getPrice();
                    int previousTotalFromItem = platePrice * previousQuantity;
                    int currentTotalFromItem = platePrice * currentQuantity;
                    int totalDiff = currentTotalFromItem - previousTotalFromItem;
                    order.setTotal(order.getTotal() + totalDiff);
                    order.getOrders().get(index).setQuantity(currentQuantity);
                    orderSubject.onNext(order);
                });
    }

    Observable<Order> ordersObservable()
    {
        return orderSubject.asObservable();
    }
}
