package com.hieupham.cleanarchitecture;

import android.app.Application;
import android.content.Context;
import com.hieupham.cleanarchitecture.feature.tasklist.TaskListActivityComponent;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module(subcomponents = { TaskListActivityComponent.class })
public class AppModule {

    @Provides
    @AppScope
    Context provideContext(Application application) {
        return application;
    }
}
