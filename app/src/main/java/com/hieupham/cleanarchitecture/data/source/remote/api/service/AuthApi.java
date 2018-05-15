package com.hieupham.cleanarchitecture.data.source.remote.api.service;

import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.source.remote.api.request.CreateTaskRequest;
import io.reactivex.Maybe;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by hieupham on 5/14/18.
 */

public interface AuthApi {

    @GET("api/task/owner")
    Maybe<List<Task>> getTasksByOwner(String uid);

    @GET("api/task/id")
    Maybe<Task> getTaskById(String uid);

    @POST("api/task/create")
    Maybe<Task> createTask(@Body CreateTaskRequest request);

    @PUT("api/task/update")
    @FormUrlEncoded
    Maybe<Task> updateTaskStatus(@Path("uid") String uid, @Path("status") String status);
}
