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
        if(order.getOrders().size() > 0) {
            Observable.from(order.getOrders())
                    .subscribeOn(Schedulers.computation())
                    .filter(orderItem1 -> orderItem1.getPlate().equals(plate))
                    .map(order.getOrders()::indexOf)
                    .doOnError(throwable -> {
                        Log.e("error", throwable.getStackTrace().toString());
                    })
                    .toList()
                    .subscribe(list -> {
                        if(list.size() > 0) {
                            OrderItem orderItem = order.getOrders().get(list.get(0));
                            order.setTotal(order.getTotal() + plate.getPrice());
                            orderItem.setQuantity(orderItem.getQuantity() + 1);
                            orderSubject.onNext(order);
                        } else {
                            createNewOrderAndNotify(plate);
                        }
                    }, throwable -> {
                        Log.e("error", throwable.getStackTrace().toString());
                    });
        } else {
           createNewOrderAndNotify(plate);
        }
    }

    void createNewOrderAndNotify(FoodPlate plate) {
        // item already in list
        OrderItem orderItem = new OrderItem(plate);
        order.getOrders().add(orderItem);
        int total = order.getTotal() + orderItem.getPlate().getPrice();
        order.setTotal(total);
        orderSubject.onNext(order);
    }

    void removeOrder(FoodPlate plate) {
        try {
            Observable.from(order.getOrders())
                    .filter(orderItem -> orderItem.getPlate().equals(plate))
                    .map(order.getOrders()::indexOf)
                    .subscribe(index -> {
                        OrderItem orderItem1 = order.getOrders().get(index);
                        int totalDiff = order.getTotal() - orderItem1.getTotal();
                        order.setTotal(totalDiff);
                        order.getOrders().remove(orderItem1);
                        orderSubject.onNext(order);
                    }, throwable -> {
                        Log.e("error", throwable.getMessage());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void itemQuantityChanged(OrderItem orderItem, int currentQuantity, int previousQuantity) {

         Observable.
                from(order.getOrders())
                .subscribeOn(Schedulers.computation())
                .filter(orderItemValue -> orderItemValue.equals(orderItem))
                .map(order.getOrders()::indexOf)
                .subscribe(index -> {
                    OrderItem orderItem1 = order.getOrders().get(index);
                    int platePrice = orderItem1.getPlate().getPrice();
                    int previousTotalFromItem = platePrice * previousQuantity;
                    int currentTotalFromItem = platePrice * currentQuantity;
                    int totalDiff = currentTotalFromItem - previousTotalFromItem;
                    order.setTotal(order.getTotal() + totalDiff);
                    order.getOrders().get(index).setQuantity(currentQuantity);
                    orderSubject.onNext(order);
                }, throwable -> {
                    Log.e("error", throwable.getStackTrace().toString());
                });
    }

    Observable<Order> ordersObservable()
    {
        return orderSubject.asObservable();
    }
}
