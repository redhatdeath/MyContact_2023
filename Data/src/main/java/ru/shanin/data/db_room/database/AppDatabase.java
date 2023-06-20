package ru.shanin.data.db_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.shanin.data.db_room.dao.PersonDao;
import ru.shanin.data.db_room.entity.PersonRoom;

@Database(
        entities = {PersonRoom.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;
    private static final String DB_NAME = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();

    public abstract PersonDao personDao();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null)
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            return database;
        }
    }
}