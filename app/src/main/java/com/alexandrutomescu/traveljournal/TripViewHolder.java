package com.alexandrutomescu.traveljournal;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView tripImage;
    private TextView tripName;
    private TextView  tripDestination;
    private TextView tripPrice;
    private RatingBar tripFavorite;
    OnTripListener onTripListener;

    public TripViewHolder(@NonNull View itemView, OnTripListener onTripListener) {
        super(itemView);

        tripImage = itemView.findViewById(R.id.tripImage);
        tripName = itemView.findViewById(R.id.tripName);
        tripDestination = itemView.findViewById(R.id.tripDestination);
        tripPrice = itemView.findViewById(R.id.tripPrice);
        tripFavorite = itemView.findViewById(R.id.tripFavorite);
        this.onTripListener = onTripListener;
        itemView.setOnClickListener(this);
    }

    public TextView getTripDestination() {
        return tripDestination;
    }

    public ImageView getTripImage() {
        return tripImage;
    }

    public TextView getTripName() {
        return tripName;
    }

    public TextView getTripPrice() {
        return tripPrice;
    }

    public RatingBar getTripFavorite() {
        return tripFavorite;
    }

    @Override
    public void onClick(View v) {
        onTripListener.onTripListener(getAdapterPosition());
    }
}
