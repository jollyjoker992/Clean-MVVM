package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.LiveData;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import java.util.List;

/**
 * Created by jollyjoker992 on 20/05/2018.
 */

public class TaskListUseCase extends UseCase {

    private TaskRepository taskRepo;

    TaskListUseCase(Transformer transformer, TaskRepository taskRepo) {
        super(transformer);
        this.taskRepo = taskRepo;
    }

    @Override
    LiveData<Resource<List<Task>>> getTasksByOwner(String uid) {
        return transformMaybe(taskRepo.getTasksByOwner(uid));
    }
}
