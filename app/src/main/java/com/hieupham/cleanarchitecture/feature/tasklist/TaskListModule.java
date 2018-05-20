package com.hieupham.cleanarchitecture.feature.tasklist;

import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.feature.DialogManager;
import com.hieupham.cleanarchitecture.feature.FragmentScope;
import com.hieupham.cleanarchitecture.feature.Navigator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class TaskListModule {

    @Provides
    @FragmentScope
    ViewModel provideViewModel(TaskRepository taskRepo) {
        return new TaskListViewModel(taskRepo);
    }

    @Provides
    @FragmentScope
    Navigator<TaskListFragment> provideNavigator(TaskListFragment fragment) {
        return new Navigator<>(fragment);
    }

    @Provides
    @FragmentScope
    TaskListAdapter provideAdapter() {
        return new TaskListAdapter();
    }

    @Provides
    @FragmentScope
    DialogManager provideDialogManager(TaskListFragment fragment) {
        return new DialogManager(fragment.getActivity());
    }
}
