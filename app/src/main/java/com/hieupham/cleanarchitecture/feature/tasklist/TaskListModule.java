package com.hieupham.cleanarchitecture.feature.tasklist;

import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.feature.ActivityScope;
import com.hieupham.cleanarchitecture.feature.DialogManager;
import com.hieupham.cleanarchitecture.feature.Navigator;
import com.hieupham.cleanarchitecture.feature.taskdetail.TaskDetailComponent;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module(subcomponents = { TaskDetailComponent.class })
public class TaskListModule {

    @Provides
    @ActivityScope
    ViewModel provideViewModel(TaskRepository taskRepo) {
        return new TaskListViewModel(taskRepo);
    }

    @Provides
    @ActivityScope
    Navigator<TaskListActivity> provideNavigator(TaskListActivity activity) {
        return new Navigator<>(activity);
    }

    @Provides
    @ActivityScope
    TaskListAdapter provideAdapter() {
        return new TaskListAdapter();
    }

    @Provides
    @ActivityScope
    DialogManager provideDialogManager(TaskListActivity activity) {
        return new DialogManager(activity);
    }
}
