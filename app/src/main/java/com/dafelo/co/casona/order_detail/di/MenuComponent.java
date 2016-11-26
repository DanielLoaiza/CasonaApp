package com.dafelo.co.casona.order_detail.di;

import com.dafelo.co.casona.internal.di.PerActivity;
import com.dafelo.co.casona.internal.di.components.ActivityComponent;
import com.dafelo.co.casona.internal.di.components.ApplicationComponent;
import com.dafelo.co.casona.internal.di.modules.ActivityModule;
import com.dafelo.co.casona.order_detail.MenuListFragment;

import dagger.Component;

/**
 * Created by root on 24/11/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MenuModule.class})
public interface MenuComponent extends ActivityComponent {
    void inject(MenuListFragment menuListFragment);
}
