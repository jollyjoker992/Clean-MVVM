package com.hieupham.cleanarchitecture.data.source.remote.api.service;

import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.remote.api.request.CreateTaskRequest;
import io.reactivex.Maybe;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hieupham on 5/14/18.
 */

public interface AuthApi {

    @GET("api/task")
    Maybe<List<Task>> getTasksByOwner(@Query("owner_id") String uid);

    @GET("api/task")
    Maybe<Task> getTaskById(@Query("id") String uid);

    @POST("api/task/create")
    Maybe<Task> createTask(@Body CreateTaskRequest request);

    @PUT("api/task/update")
    @FormUrlEncoded
    Maybe<Task> updateTaskStatus(@Path("uid") String uid, @Path("status") String status);

    @GET("api/user")
    Maybe<User> getUserById(@Query("id") String uid);
}
