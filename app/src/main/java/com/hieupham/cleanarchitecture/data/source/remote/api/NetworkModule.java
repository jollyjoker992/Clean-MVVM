package com.hieupham.cleanarchitecture.data.source.remote.api;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hieupham.cleanarchitecture.AppScope;
import com.hieupham.cleanarchitecture.data.source.remote.api.middleware.AuthInterceptor;
import com.hieupham.cleanarchitecture.data.source.remote.api.middleware.NonAuthInterceptor;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.AuthApi;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.NonAuthApi;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.ServiceGenerator;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

/**
 * Created by hieupham on 5/15/18.
 */

@Module
public class NetworkModule {

    @AppScope
    @Provides
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @AppScope
    @Provides
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @AppScope
    @Provides
    AuthInterceptor provideAuthInterceptor(Context context) {
        return new AuthInterceptor(context);
    }

    @AppScope
    @Provides
    NonAuthInterceptor provideNonAuthInterceptor(Context context) {
        return new NonAuthInterceptor(context);
    }

    @AppScope
    @Provides
    AuthApi provideAuthApi(Gson gson, AuthInterceptor interceptor) {
        return ServiceGenerator.createService("https://google.com/api/", AuthApi.class, gson,
                interceptor);
    }

    @AppScope
    @Provides
    NonAuthApi provideNonAuthApi(Gson gson, NonAuthInterceptor interceptor) {
        return ServiceGenerator.createService("https://google.com/api/", NonAuthApi.class, gson,
                interceptor);
    }

}
