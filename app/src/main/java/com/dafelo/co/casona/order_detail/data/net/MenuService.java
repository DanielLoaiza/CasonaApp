package com.dafelo.co.casona.order_detail.data.net;

import com.dafelo.co.casona.order_detail.data.entity.Sections;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by root on 25/11/16.
 */

public interface MenuService {

    @GET("api/food")
    Observable<Sections> getFoodList();
}
