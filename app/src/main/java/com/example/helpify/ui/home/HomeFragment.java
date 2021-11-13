package com.example.helpify.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.helpify.EmergencyContactAdapter;
import com.example.helpify.ItemEmergencyContact;
import com.example.helpify.R;
import com.example.helpify.databinding.FragmentHomeBinding;
import com.example.helpify.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<ItemEmergencyContact> list = new ArrayList<>();
        list.add(new ItemEmergencyContact("joshi", "", "8447999614", "brother"));
        list.add(new ItemEmergencyContact("patel", "", "8447999614", "cousin"));
        list.add(new ItemEmergencyContact("bassi", "", "8447999614", "friend"));

        EmergencyContactAdapter adapter = new EmergencyContactAdapter(list, getContext());
        binding.recyclerEmergency.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        binding.recyclerEmergency.setAdapter(adapter);

        binding.ambulanceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "102"));
                startActivity(intent);
            }
        });

        binding.fireImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "101"));
                startActivity(intent);
            }
        });

        binding.policeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "100"));
                startActivity(intent);
            }
        });

        binding.sosImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Sending Message to your Contacts", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }


}