package com.dafelo.co.casona.listeners;

import com.dafelo.co.casona.order_detail.data.entity.Food;

/**
 * Created by root on 19/11/16.
 */

public interface OnItemAddedListener {
    void onItemAdd(Food plate);
    void itemAddFinished();
}
