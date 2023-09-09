package com.example.team_player;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Player.class, Team.class},version = 1)
public abstract class DatabaseTP extends RoomDatabase {
    public abstract DoaPlayer playerDao();
    public abstract DoaTeam teamDao();

    private static volatile DatabaseTP INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DatabaseTP getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseTP.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseTP.class, "database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
