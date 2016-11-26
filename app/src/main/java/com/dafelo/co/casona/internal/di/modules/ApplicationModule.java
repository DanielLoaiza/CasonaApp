package com.dafelo.co.casona.internal.di.modules;

import android.content.Context;


import com.dafelo.co.casona.Casona;
import com.dafelo.co.casona.main.domain.threads.NewThread;
import com.dafelo.co.casona.main.domain.threads.UIThread;
import com.dafelo.co.casona.main.domain.schedulers.ObserveOn;
import com.dafelo.co.casona.main.domain.schedulers.SubscribeOn;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private final Casona application;

    public ApplicationModule(Casona application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ObserveOn provideObserveOn(UIThread observeOn) {
        return observeOn;
    }

    @Provides
    @Singleton
    SubscribeOn provideSubscribeOn(NewThread subscribeOn) {
        return subscribeOn;
    }

}
