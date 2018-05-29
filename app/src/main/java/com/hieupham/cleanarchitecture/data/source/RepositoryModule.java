package com.hieupham.cleanarchitecture.data.source;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.hieupham.cleanarchitecture.data.source.local.api.MigrationManager;
import com.hieupham.cleanarchitecture.di.AppScope;
import com.hieupham.cleanarchitecture.data.source.local.TaskLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.local.UserLocalDataSource;
import com.hieupham.cleanarchitecture.data.source.local.api.DatabaseManager;
import com.hieupham.cleanarchitecture.data.source.local.api.RoomApi;
import com.hieupham.cleanarchitecture.data.source.local.api.RoomApiImpl;
import com.hieupham.cleanarchitecture.data.source.remote.TaskRemoteDataSource;
import com.hieupham.cleanarchitecture.data.source.remote.UserRemoteDataSource;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hieupham on 5/14/18.
 */

@Module
public class RepositoryModule {

    @AppScope
    @Provides
    TaskRepository provideTaskRepo(TaskRemoteDataSource remoteDataSource,
            TaskLocalDataSource localDataSource) {
        return new TaskRepository(remoteDataSource, localDataSource);
    }

    @AppScope
    @Provides
    UserRepository provideUserRepo(UserRemoteDataSource remoteDataSource,
            UserLocalDataSource localDataSource) {
        return new UserRepository(remoteDataSource, localDataSource);
    }

    @AppScope
    @Provides
    RoomApi provideRoomApi(DatabaseManager databaseManager) {
        return new RoomApiImpl(databaseManager);
    }

    @AppScope
    @Provides
    DatabaseManager provideDatabaseManager(Context context) {
        return Room.databaseBuilder(context, DatabaseManager.class, DatabaseManager.DATABASE_NAME)
                .addMigrations(MigrationManager.MIGRATION_1_2)
                .build();
    }
}
