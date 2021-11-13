package com.example.helpify.ui.nearby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.helpify.EmergencyContactAdapter;
import com.example.helpify.ItemNearbyPlace;
import com.example.helpify.NearbyPlacesAdapter;
import com.example.helpify.databinding.FragmentNearbyBinding;

import java.util.ArrayList;

public class NearbyFragment extends Fragment {

    private FragmentNearbyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNearbyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<ItemNearbyPlace> listItemNearbyPlace = new ArrayList<>();
        listItemNearbyPlace.add(new ItemNearbyPlace("CARE New born & Child Hospital", "133-134, Club Rd, Narayana Vihar, Pocket 2, Paschim Puri, Paschim Vihar, Delhi, 110063", "+91844799614"));
        listItemNearbyPlace.add(new ItemNearbyPlace("J M Khera Hospital", "J.M Khera Hospital,495, Main Najafgarh Rd, near Krishna Mandir, Nangloi, New Delhi, Delhi 110041","+91844799614" ));
        listItemNearbyPlace.add(new ItemNearbyPlace("A One Hospital", "A1/7 Paschim Vihar, Opp- metro pillar no. 264, New Delhi, Delhi 110063", "+91844799614"));
        listItemNearbyPlace.add(new ItemNearbyPlace("Lok Clinic & Hospital", "A-2/7, Pankha Rd, Block A2, Janakpuri, New Delhi, Delhi 110058", "+91844799614"));

        NearbyPlacesAdapter adapter = new NearbyPlacesAdapter(listItemNearbyPlace, getContext());
        binding.nearbyPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        binding.nearbyPlaces.setAdapter(adapter);
        return root;
    }

}