package com.dafelo.co.casona.order_detail.domain.usecase;

/**
 * Created by root on 24/11/16.
 */

import com.dafelo.co.casona.main.domain.interactors.UseCase;
import com.dafelo.co.casona.main.domain.schedulers.ObserveOn;
import com.dafelo.co.casona.main.domain.schedulers.SubscribeOn;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by root on 23/10/16.
 */

public class GetFoodListUseCase extends UseCase {

    private final MenuRepository menuRepository;

    @Inject
    public GetFoodListUseCase(MenuRepository menuRepository, SubscribeOn subscribeOn,
                        ObserveOn observeOn) {
        super(subscribeOn, observeOn);
        this.menuRepository = menuRepository;

    }
    @Override
    protected Observable buildUseCaseObservable() {
        return this.menuRepository.getFoodList();
    }
}
