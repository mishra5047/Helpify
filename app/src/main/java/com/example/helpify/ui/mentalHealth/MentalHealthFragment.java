package com.example.helpify.ui.mentalHealth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helpify.databinding.FragmentMentalHealthBinding;
import com.example.helpify.databinding.FragmentProfileBinding;

public class MentalHealthFragment extends Fragment {

    private FragmentMentalHealthBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMentalHealthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}