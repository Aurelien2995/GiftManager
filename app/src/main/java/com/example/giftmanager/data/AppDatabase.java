package com.example.giftmanager.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.giftmanager.data.dao.PersonDao;
import com.example.giftmanager.data.dao.GiftDao;
import com.example.giftmanager.data.entities.Person;
import com.example.giftmanager.data.entities.GiftIdea;

@Database(entities = {Person.class, GiftIdea.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "giftmanager.db";
    private static volatile AppDatabase instance;

    public abstract PersonDao personDao();
    public abstract GiftDao giftDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries() // pour MVP ; changer plus tard
                            .build();
                }
            }
        }
        return instance;
    }
}
