package com.dafelo.co.casona.listeners;

import com.dafelo.co.casona.order_detail.data.entity.Food;

/**
 * Created by root on 20/11/16.
 */

public interface OnItemRemovedListener {
    void onItemRemove(Food plate);
}
