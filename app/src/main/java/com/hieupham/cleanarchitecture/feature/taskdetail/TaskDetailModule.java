package com.hieupham.cleanarchitecture.feature.taskdetail;

import com.hieupham.cleanarchitecture.feature.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class TaskDetailModule {

    @Provides
    @FragmentScope
    ViewModel provideViewModel() {
        return new TaskDetailViewModel();
    }
}
