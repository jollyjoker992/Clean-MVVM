package com.hieupham.cleanarchitecture;

import android.app.Activity;
import com.hieupham.cleanarchitecture.feature.tasklist.TaskListActivity;
import com.hieupham.cleanarchitecture.feature.tasklist.TaskListActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(TaskListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindTaskListActivity(
            TaskListActivityComponent.Builder builder);
}
