package com.hieupham.cleanarchitecture.feature.tasklist;

import com.hieupham.cleanarchitecture.feature.ActivityScope;
import com.hieupham.cleanarchitecture.FragmentBuilderModule;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by hieupham on 5/14/18.
 */

@Subcomponent(modules = { TaskListModule.class, FragmentBuilderModule.class })
@ActivityScope
public interface TaskListActivityComponent extends AndroidInjector<TaskListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TaskListActivity> {
    }
}
