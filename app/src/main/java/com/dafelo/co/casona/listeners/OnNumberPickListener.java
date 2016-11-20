package com.dafelo.co.casona.listeners;

import com.dafelo.co.casona.BO.OrderItem;

/**
 * Created by root on 20/11/16.
 */

public interface OnNumberPickListener {
    void onNumberChange(int currentVal, int previousVal, OrderItem order);
}
