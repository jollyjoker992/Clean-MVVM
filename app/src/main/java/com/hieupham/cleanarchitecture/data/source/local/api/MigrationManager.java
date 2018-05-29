package com.hieupham.cleanarchitecture.data.source.local.api;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

/**
 * Created by hieupham on 5/14/18.
 */

public class MigrationManager {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("UPDATE User SET sex = 'other'");
        }
    };
}
