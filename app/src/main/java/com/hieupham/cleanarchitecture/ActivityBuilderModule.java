package com.hieupham.cleanarchitecture;

import android.app.Activity;
import com.hieupham.cleanarchitecture.feature.ActivityScope;
import com.hieupham.cleanarchitecture.feature.main.MainActivity;
import com.hieupham.cleanarchitecture.feature.main.MainModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by hieupham on 5/14/18.
 */

/**
 * The {@link Module} class declares how to inject an {@link Activity} instance into corresponding
 * {@link Module}
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    @ActivityScope
    abstract MainActivity bindMainActivity();
}
