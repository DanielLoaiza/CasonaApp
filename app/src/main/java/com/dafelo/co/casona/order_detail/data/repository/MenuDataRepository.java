package com.dafelo.co.casona.order_detail.data.repository;

import com.dafelo.co.casona.order_detail.data.datasource.MenuDataStoreFactory;
import com.dafelo.co.casona.order_detail.data.entity.Sections;
import com.dafelo.co.casona.order_detail.domain.usecase.MenuRepository;
import com.dafelo.co.casona.order_detail.data.datasource.MenuDataStore;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by root on 24/11/16.
 */

public class MenuDataRepository implements MenuRepository {

    private final MenuDataStoreFactory menuDataStoreFactory;

    /**
     * Constructs a {@link MenuRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     */
    @Inject
    public MenuDataRepository(MenuDataStoreFactory dataStoreFactory) {
        this.menuDataStoreFactory = dataStoreFactory;
    }
    @Override
    public Observable<Sections> getFoodList() {
        final MenuDataStore menuDataStore = this.menuDataStoreFactory.createCloudDataStore();
        return menuDataStore.getFoodList();
    }
}
