package com.dafelo.co.casona.main.domain.schedulers;

/**
 * Created by root on 23/10/16.
 */

import rx.Scheduler;

public interface SubscribeOn {
    Scheduler getScheduler();
}
