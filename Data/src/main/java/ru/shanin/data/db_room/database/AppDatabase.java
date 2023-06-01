package ru.shanin.data.db_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.shanin.data.db_room.dao.PersonDao;
import ru.shanin.data.db_room.entity.PersonRoom;

@Database(entities = {PersonRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract PersonDao personDao();

    public static AppDatabase getInstance(Context context) {
        AppDatabase tempInstance = instance;
        if (tempInstance != null)
            return tempInstance;
        else tempInstance = Room
                .databaseBuilder(context,
                        AppDatabase.class,
                        AppDatabase.class.getSimpleName())
                .build();
        instance = tempInstance;
        return tempInstance;
    }
}