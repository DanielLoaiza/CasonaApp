package com.dafelo.co.casona;

import android.app.Application;
import android.content.Context;
import com.dafelo.co.casona.internal.di.components.ApplicationComponent;
import com.dafelo.co.casona.internal.di.components.DaggerApplicationComponent;
import com.dafelo.co.casona.internal.di.modules.ApplicationModule;

public class Casona extends Application {
    private ApplicationComponent applicationComponent;
    private static Casona mInstance;



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public static synchronized Casona getInstance() {
        return mInstance;
    }

}
