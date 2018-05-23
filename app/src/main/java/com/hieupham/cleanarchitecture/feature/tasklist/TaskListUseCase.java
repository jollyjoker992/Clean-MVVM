package com.hieupham.cleanarchitecture.feature.tasklist;

import android.arch.lifecycle.LiveData;
import android.support.annotation.VisibleForTesting;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.TaskRepository;
import com.hieupham.cleanarchitecture.data.source.UserRepository;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import com.hieupham.cleanarchitecture.utils.modelview.TaskModel;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by jollyjoker992 on 20/05/2018.
 */

public class TaskListUseCase extends UseCase {

    private TaskRepository taskRepo;
    private UserRepository userRepo;

    TaskListUseCase(Transformer transformer, TaskRepository taskRepo, UserRepository userRepo) {
        super(transformer);
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    LiveData<Resource<List<TaskModel>>> getTasksByOwner(String uid) {
        return transformMaybe(obsTasksByOwner(uid));
    }

    private Maybe<List<TaskModel>> obsTasksByOwner(final String uid) {
        return taskRepo.getTasksByOwner(uid)
                .flatMap(new Function<List<Task>, MaybeSource<List<TaskModel>>>() {
                    @Override
                    public MaybeSource<List<TaskModel>> apply(List<Task> tasks) throws Exception {
                        return Observable.fromIterable(tasks)
                                .flatMapMaybe(new Function<Task, MaybeSource<TaskModel>>() {
                                    @Override
                                    public MaybeSource<TaskModel> apply(final Task task)
                                            throws Exception {
                                        return userRepo.getUserById(uid)
                                                .map(new Function<User, TaskModel>() {
                                                    @Override
                                                    public TaskModel apply(User user)
                                                            throws Exception {
                                                        return TaskModel.from(task, user);
                                                    }
                                                });
                                    }
                                })
                                .toList()
                                .toMaybe();
                    }
                });
    }

    @VisibleForTesting
    Maybe<List<TaskModel>> obsTasksByOwnerTest(String uid) {
        return obsTasksByOwner(uid);
    }
}
