package com.hieupham.cleanarchitecture.data.source;

import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.local.TaskLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.remote.TaskRemoteDataSource;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
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

    public Maybe<List<Task>> getTasksByOwner(final String uid) {
        return localDataSource.getByOwner(uid)
                .flatMap(new Function<List<Task>, MaybeSource<List<Task>>>() {
                    @Override
                    public MaybeSource<List<Task>> apply(List<Task> tasks) throws Exception {
                        return tasks.isEmpty() ? remoteDataSource.getTasksByOwner(uid)
                                : Maybe.just(tasks);
                    }
                });
    }

    public Maybe<Task> getTaskById(String uid) {
        return localDataSource.getById(uid).switchIfEmpty(remoteDataSource.getTaskById(uid));
    }

    public Observable<Task> createTask(Task task) {
        return remoteDataSource.createTask(task)
                .flatMapObservable(new Function<Task, ObservableSource<? extends Task>>() {
                    @Override
                    public ObservableSource<? extends Task> apply(Task task) throws Exception {
                        return localDataSource.saveTask(task);
                    }
                });
    }

    public Observable<Task> updateTaskStatus(String uid, String status) {
        return remoteDataSource.updateTaskStatus(uid, status)
                .flatMapObservable(new Function<Task, ObservableSource<? extends Task>>() {
                    @Override
                    public ObservableSource<? extends Task> apply(Task task) throws Exception {
                        return localDataSource.saveTask(task);
                    }
                });
    }
}
