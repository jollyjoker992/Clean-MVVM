package com.hieupham.cleanarchitecture.data.source.local.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;
import com.hieupham.cleanarchitecture.data.model.User;
import io.reactivex.Maybe;

/**
 * Created by hieupham on 5/14/18.
 */

@Dao
public abstract class UserDao {

    @Query("SELECT * FROM User WHERE uid = :uid")
    public abstract Maybe<User> getById(@NonNull String uid);
}
