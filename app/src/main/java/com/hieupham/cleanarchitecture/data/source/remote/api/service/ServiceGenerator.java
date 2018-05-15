package com.hieupham.cleanarchitecture.data.source.remote.api.service;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.hieupham.cleanarchitecture.data.source.remote.api.middleware.Interceptor;
import com.hieupham.cleanarchitecture.data.source.remote.api.middleware.RxErrorHandlingCallAdapterFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hieupham on 5/14/18.
 */

public class ServiceGenerator {

    public static final int CONNECTION_TIMEOUT = 30;

    public static <T> T createService(String endPoint, Class<T> serviceClass, @NonNull Gson gson,
            Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (interceptor != null) {
            httpClientBuilder.addInterceptor(interceptor);
        }
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endPoint)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
