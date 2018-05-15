package com.hieupham.cleanarchitecture.feature.taskdetail;

import com.hieupham.cleanarchitecture.feature.FragmentScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by hieupham on 5/14/18.
 */

@Subcomponent(modules = TaskDetailModule.class)
@FragmentScope
public interface TaskDetailComponent extends AndroidInjector<TaskDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TaskDetailFragment> {
    }
}
