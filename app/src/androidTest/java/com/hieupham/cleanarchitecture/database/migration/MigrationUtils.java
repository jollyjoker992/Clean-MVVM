package com.hieupham.cleanarchitecture.database.migration;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.support.test.InstrumentationRegistry;

/**
 * Created by hieupham on 5/28/18.
 */

class MigrationUtils {

    static RoomDatabase getDatabaseAfterPerformingMigrations(
            MigrationTestHelper migrationTestHelper, Class databaseClass, String databaseName,
            Migration... migrations) {
        RoomDatabase roomDatabase =
                Room.databaseBuilder(InstrumentationRegistry.getTargetContext(), databaseClass,
                        databaseName).addMigrations(migrations).build();
        migrationTestHelper.closeWhenFinished(roomDatabase);
        return roomDatabase;
    }
}
