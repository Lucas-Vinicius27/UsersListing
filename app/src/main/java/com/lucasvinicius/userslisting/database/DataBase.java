package com.lucasvinicius.userslisting.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.repository.UsersDAO;

@Database(entities = {UsersModel.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public static DataBase instance;
    private static Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static DataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DataBase.class, "meio_ambiente")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                        }
                    })
                    .addMigrations(migration)
                    .build();
        }
        return instance;
    }

    public abstract UsersDAO usersDAO();
}
