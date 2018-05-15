package com.hieupham.cleanarchitecture.data.source;

import android.arch.lifecycle.LiveData;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.local.TaskLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.remote.TaskRemoteDataSource;
import com.hieupham.cleanarchitecture.utils.livedata.Resource;
import com.hieupham.cleanarchitecture.utils.livedata.Transformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskRepository implements Repository {

    private TaskLocalDataSource localDataSource;
    private TaskRemoteDataSource remoteDataSource;

    public TaskRepository(TaskRemoteDataSource remoteDataSource,
            TaskLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public LiveData<Resource<List<Task>>> getTasksByOwner(final String uid) {
        return Transformer.maybe(localDataSource.getByOwner(uid)
                .flatMap(new Function<List<Task>, MaybeSource<List<Task>>>() {
                    @Override
                    public MaybeSource<List<Task>> apply(List<Task> tasks) throws Exception {
                        return tasks.isEmpty() ? remoteDataSource.getTasksByOwner(uid)
                                : Maybe.just(tasks);
                    }
                }));
    }

    public LiveData<Resource<Task>> getById(String uid) {
        return Transformer.maybe(
                localDataSource.getById(uid).switchIfEmpty(remoteDataSource.getTaskById(uid)));
    }

    public LiveData<Resource<Task>> createTask(Task task) {
        return Transformer.observable(remoteDataSource.createTask(task)
                .flatMapObservable(new Function<Task, ObservableSource<? extends Task>>() {
                    @Override
                    public ObservableSource<? extends Task> apply(Task task) throws Exception {
                        return localDataSource.saveTask(task);
                    }
                }));
    }

    public LiveData<Resource<Task>> updateTaskStatus(String uid, String status) {
        return Transformer.observable(remoteDataSource.updateTaskStatus(uid, status)
                .flatMapObservable(new Function<Task, ObservableSource<? extends Task>>() {
                    @Override
                    public ObservableSource<? extends Task> apply(Task task) throws Exception {
                        return localDataSource.saveTask(task);
                    }
                }));
    }
}
