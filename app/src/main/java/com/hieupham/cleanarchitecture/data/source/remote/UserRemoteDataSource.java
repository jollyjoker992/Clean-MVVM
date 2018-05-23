package com.hieupham.cleanarchitecture.data.source.remote;

import android.support.annotation.NonNull;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.AuthApi;
import com.hieupham.cleanarchitecture.data.source.remote.api.service.NonAuthApi;
import com.hieupham.cleanarchitecture.utils.DataProvider;
import io.reactivex.Maybe;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class UserRemoteDataSource extends RemoteDataSource {

    @Inject
    UserRemoteDataSource(AuthApi authApi, NonAuthApi nonAuthApi) {
        super(authApi, nonAuthApi);
    }

    public Maybe<User> getUserById(@NonNull String uid) {
        //return authApi.getUserById(uid);
        return DataProvider.maybeUserSuccess();
    }
}
