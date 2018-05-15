package com.hieupham.cleanarchitecture.data.source.remote;

import com.hieupham.cleanarchitecture.data.source.remote.api.service.AuthApi;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.NonAuthApi;

/**
 * Created by hieupham on 5/14/18.
 */

public abstract class RemoteDataSource {

    protected AuthApi authApi;
    protected NonAuthApi nonAuthApi;

    RemoteDataSource(AuthApi authApi, NonAuthApi nonAuthApi) {
        this.authApi = authApi;
        this.nonAuthApi = nonAuthApi;
    }
}
