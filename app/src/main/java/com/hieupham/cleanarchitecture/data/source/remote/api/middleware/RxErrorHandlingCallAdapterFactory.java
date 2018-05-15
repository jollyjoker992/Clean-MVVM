package com.hieupham.cleanarchitecture.data.source.remote.api.middleware;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by hieupham on 5/15/18.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return null;
    }
}
