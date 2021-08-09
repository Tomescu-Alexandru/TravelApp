package com.alexandrutomescu.traveljournal;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.jar.JarEntry;

public class Trip implements Serializable {
    private int id;
    private int imgSource;
    private String tripName;
    private String destination;
    private float rating;
    private float favorite;
    private float price;
    private Date startDate, endDate;
    private TripType tripType;

    public Trip(int id, int imgSource, String tripName, String destination, float rating, float favorite, float price, Date startDate, Date endDate, TripType tripType) {
        this.id = id;
        this.imgSource = imgSource;
        this.tripName = tripName;
        this.destination = destination;
        this.rating = rating;
        this.favorite = favorite;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripType = tripType;
    }

    public Trip() {

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgSource() {
        return imgSource;
    }

    public void setImgSource(int imgSource) {
        this.imgSource = imgSource;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getFavorite() {
        return favorite;
    }

    public void setFavorite(float favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                imgSource == trip.imgSource &&
                Float.compare(trip.rating, rating) == 0 &&
                Float.compare(trip.favorite, favorite) == 0 &&
                Float.compare(trip.price, price) == 0 &&
                Objects.equals(tripName, trip.tripName) &&
                Objects.equals(destination, trip.destination) &&
                Objects.equals(startDate, trip.startDate) &&
                Objects.equals(endDate, trip.endDate) &&
                tripType == trip.tripType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgSource, tripName, destination, rating, favorite, price, startDate, endDate, tripType);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", imgSource=" + imgSource +
                ", tripName='" + tripName + '\'' +
                ", destination='" + destination + '\'' +
                ", rating=" + rating +
                ", favorite=" + favorite +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tripType=" + tripType +
                '}';
    }
}
