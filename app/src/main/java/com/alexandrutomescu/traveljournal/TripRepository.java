package com.alexandrutomescu.traveljournal;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {

    public TripDao tripDao;
    private LiveData<List<Trip>> allTrips;

    TripRepository(Application application){
        TripDatabase tripDatabase = TripDatabase.getInstance(application);
        tripDao= tripDatabase.getDao();
        allTrips = tripDao.getAllTrips();
    }

    LiveData<List<Trip>> getAllTrips(){
        return allTrips;
    }

    void insert(Trip trip){
        TripDatabase.databaseWriteExecutor.execute(() ->
        tripDao.insertTrip(trip));
    }

}
