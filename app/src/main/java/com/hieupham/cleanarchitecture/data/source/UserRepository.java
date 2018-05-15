package com.hieupham.cleanarchitecture.data.source;

import com.hieupham.cleanarchitecture.data.source.local.UserLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.remote.UserRemoteDataSource;

/**
 * Created by hieupham on 5/14/18.
 */

public class UserRepository implements Repository {

    private UserLocalDataSource localDataSource;
    private UserRemoteDataSource remoteDataSource;

    public UserRepository(UserRemoteDataSource remoteDataSource,
            UserLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }
}
