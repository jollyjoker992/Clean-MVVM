package com.hieupham.cleanarchitecture;

import dagger.android.HasActivityInjector;

/**
 * Created by hieupham on 5/14/18.
 */

public class MvvmApplication extends DaggerApplication implements HasActivityInjector {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
