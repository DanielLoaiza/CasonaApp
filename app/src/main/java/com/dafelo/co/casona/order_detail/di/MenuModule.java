package com.dafelo.co.casona.order_detail.di;

/**
 * Created by root on 24/11/16.
 */

import com.dafelo.co.casona.internal.di.PerActivity;
import com.dafelo.co.casona.main.domain.interactors.UseCase;
import com.dafelo.co.casona.main.domain.schedulers.ObserveOn;
import com.dafelo.co.casona.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.casona.order_detail.domain.usecase.GetFoodListUseCase;
import com.dafelo.co.casona.order_detail.domain.usecase.MenuRepository;
import com.dafelo.co.casona.order_detail.interfaces.MenuListContract;
import com.dafelo.co.casona.order_detail.presenter.MenuListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MenuModule {

    @Provides
    @PerActivity
    MenuListContract.Presenter providePresenter(MenuListPresenter menuListPresenter){
        return menuListPresenter;
    }

    @Provides
    @PerActivity
    @Named("foodList")
    UseCase provideGetFoodListUseCase(
            MenuRepository menuRepository,
            SubscribeOn subscribeOn, ObserveOn observeOn) {
        return new GetFoodListUseCase(menuRepository, subscribeOn, observeOn);
    }
}
