package com.hieupham.cleanarchitecture.data.source.local.api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.hieupham.cleanarchitecture.data.model.Task;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.local.api.dao.TaskDao;
import com.hieupham.cleanarchitecture.data.source.local.api.dao.UserDao;

/**
 * Created by hieupham on 5/14/18.
 */

@Database(entities = { User.class, Task.class }, version = 2)
public abstract class DatabaseManager extends RoomDatabase {

    public static final String DATABASE_NAME = "room_db";

    public abstract UserDao userDao();

    public abstract TaskDao taskDao();
}
