package com.alexandrutomescu.traveljournal;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Trip.class}, version = 1)
public abstract class TripDatabase extends RoomDatabase {

    public abstract TripDao getDao();
    private static TripDatabase INSTANCE;

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
