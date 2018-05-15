package com.hieupham.cleanarchitecture.data.source.local;

import com.hieupham.cleanarchitecture.data.source.local.api.RoomApi;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.utils.action.Action2;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskLocalDataSource extends LocalDataSource {

    @Inject
    TaskLocalDataSource(RoomApi roomApi) {
        super(roomApi);
    }

    public Maybe<Task> getById(final String uid) {
        return maybe(new Function<RoomApi, Maybe<Task>>() {
            @Override
            public Maybe<Task> apply(RoomApi roomApi) throws Exception {
                return roomApi.taskDao().getById(uid);
            }
        });
    }

    public Maybe<List<Task>> getByOwner(final String uid) {
        return maybe(new Function<RoomApi, Maybe<List<Task>>>() {
            @Override
            public Maybe<List<Task>> apply(RoomApi roomApi) throws Exception {
                return roomApi.taskDao().getByOwner(uid);
            }
        });
    }

    public Observable<Task> saveTask(final Task task) {
        return observable(new Action2<ObservableEmitter<? super Task>, RoomApi>() {
            @Override
            public void call(ObservableEmitter<? super Task> emitter, RoomApi roomApi) {
                roomApi.taskDao().save(task);
                emitter.onNext(task);
                emitter.onComplete();
            }
        });
    }

    public Observable<List<Task>> saveTasks(final List<Task> tasks) {
        return observable(new Action2<ObservableEmitter<? super List<Task>>, RoomApi>() {
            @Override
            public void call(ObservableEmitter<? super List<Task>> emitter, RoomApi roomApi) {
                roomApi.taskDao().save(tasks);
                emitter.onNext(tasks);
                emitter.onComplete();
            }
        });
    }
}
