package com.hieupham.cleanarchitecture.data.source.remote;

import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.remote.api.request.CreateTaskRequest;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.AuthApi;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.NonAuthApi;
import com.hieupham.cleanarchitecture.utils.DataProvider;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class TaskRemoteDataSource extends RemoteDataSource {

    @Inject
    public TaskRemoteDataSource(AuthApi authApi, NonAuthApi nonAuthApi) {
        super(authApi, nonAuthApi);
    }

    public Maybe<List<Task>> getTasksByOwner(String uid) {
        //return authApi.getTasksByOwner(uid).subscribeOn(Schedulers.io());
        return DataProvider.maybeTasksOwnerDelaySuccess();
    }

    public Maybe<Task> getTaskById(String uid) {
        return authApi.getTaskById(uid).subscribeOn(Schedulers.io());
    }

    public Maybe<Task> createTask(Task task) {
        return authApi.createTask(new CreateTaskRequest(task)).subscribeOn(Schedulers.io());
    }

    public Maybe<Task> updateTaskStatus(String uid, String status) {
        return authApi.updateTaskStatus(uid, status).subscribeOn(Schedulers.io());
    }
}
