package com.dafelo.co.casona.order_detail.data.datasource;

import com.dafelo.co.casona.main.data.ApiConnection;
import com.dafelo.co.casona.order_detail.data.net.OrderService;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by root on 25/11/16.
 */

public class OrderDataStoreFactory {

    @Inject
    public OrderDataStoreFactory() {
    }
    /**
     * Create {@link OrderDataStore} to retrieve data from the Cloud.
     */
    public OrderDataStore createCloudDataStore() {
        ApiConnection apiConnection = new ApiConnection();
        Retrofit retrofit = apiConnection.createClient();
        OrderService orderService = retrofit.create(OrderService.class);

        return new CloudOrderDataStore(orderService);
    }
}
