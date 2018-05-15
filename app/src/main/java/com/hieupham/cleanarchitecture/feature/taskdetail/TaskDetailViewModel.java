package com.hieupham.cleanarchitecture.feature.taskdetail;

import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.data.source.UserRepository;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskDetailViewModel extends ViewModel {

    private TaskRepository taskRepo;
    private UserRepository userRepo;

    TaskDetailViewModel(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }
}
