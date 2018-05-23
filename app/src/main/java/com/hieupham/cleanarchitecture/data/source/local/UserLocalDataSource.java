package com.hieupham.cleanarchitecture.data.source.local;

import android.support.annotation.NonNull;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.local.api.RoomApi;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;
import javax.inject.Inject;

/**
 * Created by hieupham on 5/14/18.
 */

public class UserLocalDataSource extends LocalDataSource {
    @Inject
    UserLocalDataSource(RoomApi roomApi) {
        super(roomApi);
    }

    public Maybe<User> getUserById(@NonNull final String uid) {
        return maybe(new Function<RoomApi, Maybe<User>>() {
            @Override
            public Maybe<User> apply(RoomApi roomApi) throws Exception {
                return roomApi.userDao().getById(uid);
            }
        });
    }
}
