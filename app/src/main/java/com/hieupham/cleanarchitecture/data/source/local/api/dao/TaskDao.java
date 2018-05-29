package com.hieupham.cleanarchitecture.data.source.local.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.VisibleForTesting;
import com.hieupham.cleanarchitecture.data.model.Task;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Created by hieupham on 5/14/18.
 */

@Dao
public abstract class TaskDao {

    @Query("SELECT * FROM Task WHERE uid = :uid")
    public abstract Maybe<Task> getById(String uid);

    @Query("SELECT * FROM Task WHERE owner_uid = :uid")
    public abstract Maybe<List<Task>> getByOwner(String uid);

    @Query("SELECT * FROM Task WHERE owner_uid = :uid AND status IN (:status)")
    public abstract Maybe<List<Task>> getByOwner(String uid, String... status);

    @Query("SELECT * FROM Task")
    public abstract Maybe<List<Task>> get();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void save(List<Task> tasks);

    @Query("DELETE FROM Task")
    @VisibleForTesting
    public abstract void delete();
}
