package com.dafelo.co.casona.order_detail.data.datasource;

import com.dafelo.co.casona.main.data.ApiConnection;
import com.dafelo.co.casona.order_detail.data.net.MenuService;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by root on 25/11/16.
 */

public class MenuDataStoreFactory {

    @Inject
    public MenuDataStoreFactory() {
    }
    /**
     * Create {@link MenuDataStore} to retrieve data from the Cloud.
     */
    public MenuDataStore createCloudDataStore() {
        ApiConnection apiConnection = new ApiConnection();
        Retrofit retrofit = apiConnection.createClient();
        MenuService menuService = retrofit.create(MenuService.class);

        return new CloudMenuDataStore(menuService);
    }
}
