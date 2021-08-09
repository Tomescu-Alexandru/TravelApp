package com.alexandrutomescu.traveljournal;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static int toInt(TripType tripType){
        if(tripType.equals(TripType.CITY_BREAK))
            return 1;
        else if (tripType.equals(TripType.SEA_SIDE))
            return 2;
        else return 3;
    }

    @TypeConverter
    public static TripType toTripType(int type){
        if(type == 1)
            return TripType.CITY_BREAK;
        else if(type==2)
            return TripType.SEA_SIDE;
        else return TripType.MOUNTAINS;
    }

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
