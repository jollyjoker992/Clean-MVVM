package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import java.util.List;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskListViewModel extends ViewModel {

    private final MutableLiveData<String> uid = new MutableLiveData<>();
    private LiveData<Resource<List<TaskModel>>> liveTasks = Transformations.switchMap(uid,
            new Function<String, LiveData<Resource<List<TaskModel>>>>() {
                @Override
                public LiveData<Resource<List<TaskModel>>> apply(String uid) {
                    return useCase.getTasksByOwner(uid);
                }
            });

    TaskListViewModel(@Nullable UseCase useCase) {
        super(useCase);
    }

    @Override
    void init(String uid) {
        this.uid.setValue(uid);
    }

    @Override
    LiveData<Resource<List<TaskModel>>> tasks() {
        return liveTasks;
    }

}
