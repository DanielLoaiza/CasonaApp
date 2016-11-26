package com.dafelo.co.casona.order_detail.data.datasource;

import com.dafelo.co.casona.order_detail.data.entity.Sections;

import rx.Observable;

/**
 * Created by root on 25/11/16.
 */

public interface MenuDataStore {
    Observable<Sections> getFoodList();
}
