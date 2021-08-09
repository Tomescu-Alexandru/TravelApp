package com.alexandrutomescu.traveljournal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {

    @Query("SELECT * FROM Trip")
    LiveData<List<Trip>> getAllTrips();

    @Insert
    void insertTrip(Trip trip);



}
