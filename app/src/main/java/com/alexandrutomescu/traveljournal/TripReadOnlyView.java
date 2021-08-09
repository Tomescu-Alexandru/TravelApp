package com.alexandrutomescu.traveljournal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;

public class TripReadOnlyView extends Fragment {

    private Button editButton;
    private Trip trip;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_read_only, container, false);

        bundle= this.getArguments();

        trip = (Trip) bundle.getSerializable("trip");

        editButton = view.findViewById(R.id.edit_button);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("add",false);
                bundle.putSerializable("trip",trip);
                TripForm tripform = new TripForm();
                tripform.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, tripform).commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
