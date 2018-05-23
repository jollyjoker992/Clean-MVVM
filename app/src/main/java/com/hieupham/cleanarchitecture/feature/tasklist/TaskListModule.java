package com.hieupham.cleanarchitecture.feature.tasklist;

import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.data.source.UserRepository;
import com.hieupham.cleanarchitecture.feature.DialogManager;
import com.hieupham.cleanarchitecture.di.FragmentScope;
import com.hieupham.cleanarchitecture.feature.Navigator;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class TaskListModule {

    @Provides
    @FragmentScope
    ViewModel provideViewModel(UseCase useCase) {
        return new TaskListViewModel(useCase);
    }

    @Provides
    @FragmentScope
    UseCase provideUseCase(Transformer transformer, TaskRepository taskRepo,
            UserRepository userRepo) {
        return new TaskListUseCase(transformer, taskRepo, userRepo);
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
