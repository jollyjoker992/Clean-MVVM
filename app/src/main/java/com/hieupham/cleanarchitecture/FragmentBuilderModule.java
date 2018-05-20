package com.hieupham.cleanarchitecture;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.hieupham.cleanarchitecture.feature.FragmentScope;
import com.hieupham.cleanarchitecture.feature.taskdetail.TaskDetailFragment;
import com.hieupham.cleanarchitecture.feature.taskdetail.TaskDetailModule;
import com.hieupham.cleanarchitecture.feature.tasklist.TaskListFragment;
import com.hieupham.cleanarchitecture.feature.tasklist.TaskListModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by hieupham on 5/14/18.
 */

/**
 * The {@link Module} class declares how to inject a {@link Fragment} instance into corresponding
 * {@link Module} <br>
 * Because Dagger 2 is lazy initialization so you don't need to care about why we need to define
 * how
 * to bind all {@link Fragment} here. <br>
 * <b>NOTE:</b> You should include this {@link Module} in the {@link Activity} module that uses
 * {@link Fragment} to interact.
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = TaskListModule.class)
    @FragmentScope
    abstract TaskListFragment bindTaskListFragment();

    @ContributesAndroidInjector(modules = TaskDetailModule.class)
    @FragmentScope
    abstract TaskDetailFragment bindTaskDetailFragment();
}
