package com.example.helpify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

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

            // Do your networking task or background work here.
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    boolean isSuccessful = true;

                    // Choose a stop animation if your call was successful or not
                    if (isSuccessful) {
                        binding.loginButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, () -> {
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        });
                    } else {
                        binding.loginButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                    }
                }
            }, 2000);
        });
    }
}