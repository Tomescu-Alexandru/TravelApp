package com.alexandrutomescu.traveljournal;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Trip.class}, version = 1)
public abstract class TripDatabase extends RoomDatabase {

    public abstract TripDao getDao();
    private static TripDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TripDatabase getInstance(final Context context){

        if(INSTANCE!=null){
            synchronized (TripDatabase.class){
                if(INSTANCE!=null) {
                    INSTANCE = Room.databaseBuilder(context, TripDatabase.class, "DATABASE").build();
                }
            }
        }
        return INSTANCE;
    }
}
