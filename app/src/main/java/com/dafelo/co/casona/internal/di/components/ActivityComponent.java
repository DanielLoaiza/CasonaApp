package com.dafelo.co.casona.internal.di.components;

/**
 * Created by root on 24/10/16.
 */

import android.app.Activity;


import com.dafelo.co.casona.internal.di.PerActivity;
import com.dafelo.co.casona.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.dafelo.co.casona.internal.di.PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}