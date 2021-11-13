package com.example.helpify.ui.nearby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helpify.databinding.FragmentNearbyBinding;

public class NearbyFragment extends Fragment {

    private FragmentNearbyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNearbyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}