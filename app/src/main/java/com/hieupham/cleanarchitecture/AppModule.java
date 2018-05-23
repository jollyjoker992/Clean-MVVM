package com.hieupham.cleanarchitecture;

import android.app.Application;
import android.content.Context;
import com.hieupham.cleanarchitecture.di.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class AppModule {

    @Provides
    @AppScope
    Context provideContext(Application application) {
        return application;
    }
}
