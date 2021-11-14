package com.example.helpify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.helpify.databinding.ActivityLoginBinding;
import com.royrodriguez.transitionbutton.TransitionButton;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(v -> {
            // Start the loading animation when the user tap the button
            binding.loginButton.startAnimation();
            navigate();
        });
    }

    void navigate(){

        String number = binding.mobileNoEnter.getText().toString();

        if(number.isEmpty() || number.length() < 10){
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            binding.mobileNoEnter.setError("Invalid");
            return;
        }

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("number", number);
        startActivity(intent);
    }
}