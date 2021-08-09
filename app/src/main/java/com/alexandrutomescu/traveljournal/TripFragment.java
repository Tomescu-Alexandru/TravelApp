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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TripFragment extends Fragment implements OnTripListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TripAdapter tripAdapter;
    private List<Trip> trips = new ArrayList<Trip>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle.getBoolean("main")==false)
        handleBundle(bundle);


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTrips);

        return view;
    }

    private void handleBundle(Bundle bundle) {
        Trip trip = (Trip) bundle.getSerializable("trip");
        Log.d(TAG, String.valueOf(trip.getId()));
        if (bundle.getBoolean("add") == true) {
            trip.setId(trips.get(trips.size() - 1).getId() + 1);
            trips.add(trip);
        } else {
            Log.d(TAG, "AI AJUNS UNDE TREBUIE");
            for (int i=0;i< trips.size();i++) {
                Log.d(TAG, String.valueOf(trips.get(i).getId()));
                if (trips.get(i).getId() == trip.getId()) {
                    trips.set(i, trip);
                    break;
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        addTrip();

        tripAdapter = new TripAdapter(trips, this);
        recyclerView.setAdapter(tripAdapter);

    }

    void addTrip() {
        trips.add(new Trip(1, R.drawable.bucharest, "Romania", "Bucuresti", 5, 1, 10,
                new Date(2021, 10, 4), new Date(2021, 10, 4), TripType.CITY_BREAK));

        trips.add(new Trip(2, R.drawable.ic_home, "Italia", "Roma", 5, 1, 10,
                new Date(2021, 10, 4), new Date(2021, 10, 4), TripType.CITY_BREAK));
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

