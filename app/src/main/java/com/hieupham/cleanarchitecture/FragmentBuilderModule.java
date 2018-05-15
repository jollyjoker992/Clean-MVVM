package com.hieupham.cleanarchitecture;

import android.support.v4.app.Fragment;
import com.hieupham.cleanarchitecture.feature.taskdetail.TaskDetailComponent;
import com.hieupham.cleanarchitecture.feature.taskdetail.TaskDetailFragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public abstract class FragmentBuilderModule {

    @Binds
    @IntoMap
    @FragmentKey(TaskDetailFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindTaskDetailFragment(
            TaskDetailComponent.Builder builder);
}
