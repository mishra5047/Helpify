package com.example.helpify.ui.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helpify.StartScreen;
import com.example.helpify.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.logoutButton.setOnClickListener(view -> {
            preferences = requireContext().getSharedPreferences("Helpify", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("number");
            editor.commit();
            startActivity(new Intent(getContext(), StartScreen.class));
        });

        return root;
    }
}