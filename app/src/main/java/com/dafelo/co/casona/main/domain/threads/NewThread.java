package com.dafelo.co.casona.main.domain.threads;


import com.dafelo.co.casona.main.domain.schedulers.SubscribeOn;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by root on 24/10/16.
 */

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@Singleton
public class NewThread implements SubscribeOn {
    @Inject
    public NewThread() {}

    @Override
    public Scheduler getScheduler() {
        return Schedulers.newThread();
    }
}
