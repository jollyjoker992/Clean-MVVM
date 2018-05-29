package com.hieupham.cleanarchitecture.database.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.hieupham.cleanarchitecture.data.model.User;
import com.hieupham.cleanarchitecture.data.source.local.api.DatabaseManager;
import com.hieupham.cleanarchitecture.data.source.local.api.MigrationManager;
import com.hieupham.cleanarchitecture.database.data.DataProvider;
import io.reactivex.Maybe;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by hieupham on 5/28/18.
 */

@RunWith(AndroidJUnit4.class)
public class MigrationTest {

    @Rule
    public MigrationTestHelper migrationTestHelper =
            new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                    DatabaseManager.class.getCanonicalName(),
                    new FrameworkSQLiteOpenHelperFactory());

    @Test
    public void verifyMigrationFrom1_2() throws IOException {

        final User verifyUser = DataProvider.user1();

        SupportSQLiteDatabase database =
                migrationTestHelper.createDatabase(DatabaseManager.DATABASE_NAME, 1);
        insertUser(verifyUser, database);
        insertUser(DataProvider.user2(), database);
        insertUser(DataProvider.user3(), database);
        insertUser(DataProvider.user4(), database);
        database.close();

        migrationTestHelper.runMigrationsAndValidate(DatabaseManager.DATABASE_NAME, 2, true,
                MigrationManager.MIGRATION_1_2);

        DatabaseManager databaseManager =
                (DatabaseManager) MigrationUtils.getDatabaseAfterPerformingMigrations(
                        migrationTestHelper, DatabaseManager.class, DatabaseManager.DATABASE_NAME,
                        MigrationManager.MIGRATION_1_2);
        verifyUser.setSex(User.Sex.FEMALE);
        databaseManager.userDao().save(verifyUser);
        Maybe<User> stream = databaseManager.userDao().getById(verifyUser.getUid());
        TestObserver<User> observer = new TestObserver<>();
        stream.subscribe(observer);

        observer.assertValue(new Predicate<User>() {
            @Override
            public boolean test(User user) throws Exception {
                return user.getSex().equals(User.Sex.FEMALE);
            }
        });
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertTerminated();
    }

    @Test
    public void verifyMigrationFrom2_3() {

    }

    @Test
    public void verifyMigrationFrom3_4() {

    }

    private void insertUser(User user, SupportSQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put("uid", user.getUid());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        database.insert("User", SQLiteDatabase.CONFLICT_REPLACE, values);
    }
}
