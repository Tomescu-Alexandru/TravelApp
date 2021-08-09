package com.alexandrutomescu.traveljournal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class TripForm extends Fragment {

    private Trip trip;
    private View view;
    EditText tripName, destination, price, startDate, endDate;
    DatePickerDialog.OnDateSetListener setListener;
    RatingBar ratingBar;
    RadioButton cityBreak, seaSide, mountains;
    Button saveButton;
    private boolean adding=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frament_trip_form, container, false);

        Bundle bundle = this.getArguments();
        adding = bundle.getBoolean("add");
        trip = (Trip) bundle.getSerializable("trip");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        makeFieldsReferances();
        datePicker();

        if(!adding)
            populateFields();


        saveButton = view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            saveMethod();
        });
    }

    private void saveMethod() {
        trip.setTripName(tripName.getText().toString());
        trip.setDestination(destination.getText().toString());

        if (cityBreak.isChecked())
            trip.setTripType(TripType.CITY_BREAK);
        else if (seaSide.isChecked())
            trip.setTripType(TripType.SEA_SIDE);
        else trip.setTripType(TripType.MOUNTAINS);

        trip.setPrice(Float.valueOf(price.getText().toString()));
        trip.setRating(ratingBar.getRating());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = dateFormat.parse(startDate.getText().toString());
            Date date2 = dateFormat.parse(endDate.getText().toString());
            trip.setStartDate(date1);
            trip.setEndDate(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("trip",trip);
        bundle.putBoolean("add",adding);
        bundle.putBoolean("main", false);
        TripFragment tripFragment= new TripFragment();
        tripFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, tripFragment).commit();
    }

    private void populateFields() {
        tripName.setText(trip.getTripName());
        destination.setText(trip.getDestination());

        if (trip.getTripType() == TripType.CITY_BREAK)
            cityBreak.setChecked(true);
        else if (trip.getTripType() == TripType.SEA_SIDE)
            seaSide.setChecked(true);
        else
            mountains.setChecked(true);

        price.setText(String.valueOf(trip.getPrice()));
        Date date = trip.getStartDate();
        String dateText = date.getDay() - 1 + "/" + date.getMonth() + "/" + date.getYear();
        startDate.setText(dateText);
        date = trip.getEndDate();
        dateText = date.getDay() - 1 + "/" + date.getMonth() + "/" + date.getYear();
        endDate.setText(dateText);
        ratingBar.setRating(trip.getRating());
    }

    private void makeFieldsReferances() {


        tripName = view.findViewById(R.id.trip_name_text);
        destination = view.findViewById(R.id.trip_destination_text);
        price = view.findViewById(R.id.trip_price_text);
        startDate = view.findViewById(R.id.trip_start_date_text);
        endDate = view.findViewById(R.id.trip_end_date_text);
        ratingBar = view.findViewById(R.id.trip_rating);
        cityBreak = view.findViewById(R.id.city_break);
        seaSide = view.findViewById(R.id.sea_side);
        mountains = view.findViewById(R.id.mountains);


    }

    private void datePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        startDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getActivity(), (view, year12, month12, day12) -> {
                month12 = month12 + 1;
                String date = day12 + "/" + month12 + "/" + year12;
                startDate.setText(date);
            }, year, month, day);
            datePickerDialog.show();
        });

        endDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getActivity(), (view, year1, month1, day1) -> {
                month1 = month1 + 1;
                String date = day1 + "/" + month1 + "/" + year1;
                endDate.setText(date);
            }, year, month, day);
            datePickerDialog.show();
        });

    }


}
