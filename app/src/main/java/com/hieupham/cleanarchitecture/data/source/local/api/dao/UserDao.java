package com.hieupham.cleanarchitecture.data.source.local.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.hieupham.cleanarchitecture.data.model.User;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Created by hieupham on 5/14/18.
 */

@Dao
public abstract class UserDao {

    @Query("SELECT * FROM User WHERE uid = :uid")
    public abstract Maybe<User> getById(@NonNull String uid);

    @Query("SELECT * FROM USER")
    public abstract Maybe<List<User>> get();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(List<User> users);

    @VisibleForTesting
    @Query("DELETE FROM User")
    public abstract void delete();
}
