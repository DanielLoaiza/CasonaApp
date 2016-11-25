package com.dafelo.co.casona.order_detail.di;

import com.dafelo.co.casona.internal.di.components.ApplicationComponent;
import com.dafelo.co.casona.internal.di.modules.PerFragment;
import com.dafelo.co.casona.order_detail.MenuListFragment;

import dagger.Component;

/**
 * Created by root on 24/11/16.
 */

@PerFragment
@Component( modules = {MenuModule.class}, dependencies = {ApplicationComponent.class} )
public interface MenuComponent {
    void inject(MenuListFragment menuListFragment);
}
