package com.alexandrutomescu.traveljournal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TripFragment extends Fragment implements OnTripListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TripAdapter tripAdapter;
    private List<Trip> trips = new ArrayList<Trip>();
    private TripViewModel tripViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        View view = inflater.inflate(R.layout.fragment_home, container, false);

       // tripViewModel = new TripViewModel(this).get(TripViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerViewTrips);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);



        tripAdapter = new TripAdapter(trips, this);
        recyclerView.setAdapter(tripAdapter);

    }


    @Override
    public void onTripListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("trip", trips.get(position));
        TripReadOnlyView tripReadOnlyView = new TripReadOnlyView();
        tripReadOnlyView.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, tripReadOnlyView).commit();
    }


}

