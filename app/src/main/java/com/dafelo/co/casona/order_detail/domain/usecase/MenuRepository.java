package com.dafelo.co.casona.order_detail.domain.usecase;

import com.dafelo.co.casona.order_detail.data.entity.Sections;

import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public interface MenuRepository {
    Observable<Sections> getFoodList();
}
