package com.dafelo.co.casona.order_detail.di;

/**
 * Created by root on 24/11/16.
 */

import com.dafelo.co.casona.internal.di.modules.PerFragment;
import com.dafelo.co.casona.order_detail.interfaces.MenuListContract;
import com.dafelo.co.casona.order_detail.presenter.MenuListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MenuModule {
    @Provides
    @PerFragment
    public MenuListContract.Presenter providePresenter(){
        return new MenuListPresenter();
    }
}
