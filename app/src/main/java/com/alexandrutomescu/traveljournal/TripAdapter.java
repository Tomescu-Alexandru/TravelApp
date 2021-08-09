package com.alexandrutomescu.traveljournal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder>{

    private List<Trip> trips;
    private OnTripListener onTripListener;
    public TripAdapter(List<Trip> trips, OnTripListener onTripListener){
        this.trips=trips;
        this.onTripListener=onTripListener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView, onTripListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.getTripName().setText(currentTrip.getTripName());
        holder.getTripDestination().setText(currentTrip.getDestination());
        holder.getTripPrice().setText(String.valueOf(currentTrip.getPrice()));
        holder.getTripFavorite().setRating(currentTrip.getFavorite());
        holder.getTripImage().setImageResource(currentTrip.getImgSource());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}

