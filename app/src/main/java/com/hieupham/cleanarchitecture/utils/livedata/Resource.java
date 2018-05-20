package com.hieupham.cleanarchitecture.utils.livedata;

import android.arch.lifecycle.LiveData;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import static com.hieupham.cleanarchitecture.utils.livedata.Resource.Status.ERROR;
import static com.hieupham.cleanarchitecture.utils.livedata.Resource.Status.LOADING;
import static com.hieupham.cleanarchitecture.utils.livedata.Resource.Status.SUCCESS;

/**
 * Created by hieupham on 5/18/18.
 */

/**
 * Wrapper class to handle data state when use with {@link LiveData}
 *
 * @param <T> Data type
 */
public class Resource<T> {

    @IntDef({ SUCCESS, LOADING, ERROR })
    @interface Status {
        int SUCCESS = 0x00;
        int LOADING = 0x01;
        int ERROR = 0x02;
    }

    @Nullable
    private T data;
    @Nullable
    private Throwable throwable;
    @Status
    private int status;

    private Resource(@Status int status, @Nullable T data, @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(Throwable throwable, @Nullable T data) {
        return new Resource<>(ERROR, data, throwable);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public T data() {
        return data;
    }

    public Throwable throwable() {
        return throwable;
    }

    public boolean isSuccessful() {
        return status == SUCCESS;
    }

    public boolean isError() {
        return status == ERROR;
    }

    public boolean isLoading() {
        return status == LOADING;
    }
}
