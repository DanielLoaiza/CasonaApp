package com.dafelo.co.casona.order_detail.data.datasource;

import com.dafelo.co.casona.order_detail.data.entity.Sections;
import com.dafelo.co.casona.order_detail.data.net.MenuService;

import rx.Observable;

/**
 * Created by root on 25/11/16.
 */

public class CloudMenuDataStore implements MenuDataStore {

    private final MenuService menuService;

    /**
     * Construct a {@link MenuDataStore} based on connections to the api (Cloud).
     */
    CloudMenuDataStore(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public Observable<Sections> getFoodList() {
        return menuService.getFoodList();
    }
}
