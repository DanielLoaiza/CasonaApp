package com.dafelo.co.casona.internal.di.components;

import android.content.Context;

import com.dafelo.co.casona.internal.di.modules.ApplicationModule;
import com.dafelo.co.casona.main.BaseActivity;
import com.dafelo.co.casona.main.domain.schedulers.ObserveOn;
import com.dafelo.co.casona.main.domain.schedulers.SubscribeOn;
import com.dafelo.co.casona.order_detail.domain.usecase.MenuRepository;

import javax.inject.Singleton;
import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
    @Singleton // Constraints this component to one-per-application or unscoped bindings.
    @Component(modules = ApplicationModule.class)
    public interface ApplicationComponent {
        void inject(BaseActivity baseActivity);

        //Exposed to sub-graphs.
        Context context();
        ObserveOn observeOn();
        SubscribeOn subscribeOn();
        MenuRepository menuRepository();
}
