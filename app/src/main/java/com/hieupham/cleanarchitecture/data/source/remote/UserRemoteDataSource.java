package com.hieupham.cleanarchitecture.data.source.remote;

import com.hieupham.cleanarchitecture.data.source.remote.api.service.AuthApi;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.NonAuthApi;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class UserRemoteDataSource extends RemoteDataSource {
    @Inject
    UserRemoteDataSource(AuthApi authApi, NonAuthApi nonAuthApi) {
        super(authApi, nonAuthApi);
    }
}
