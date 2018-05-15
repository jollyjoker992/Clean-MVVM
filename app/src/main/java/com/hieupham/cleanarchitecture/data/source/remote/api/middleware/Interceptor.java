package com.hieupham.cleanarchitecture.data.source.remote.api.middleware;

import android.content.Context;
import java.io.IOException;
import okhttp3.Response;

/**
 * Created by hieupham on 5/15/18.
 */

public abstract class Interceptor implements okhttp3.Interceptor {

    protected Context context;

    Interceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
