package com.dafelo.co.casona.internal.di.modules;

/**
 * Created by root on 24/10/16.
 */

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.dafelo.co.casona.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
