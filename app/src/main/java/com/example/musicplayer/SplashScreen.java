package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    // Time for splash screen to load to another page
    private static int SPLASH_SCREEN = 1800;

    // Variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView title, tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        } else {
            // FLAG_FULLSCREEN is deprecated in android R
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
        setContentView(R.layout.activity_splash_screen);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Hooks
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        tagline = findViewById(R.id.tagline);

        // Assign Animation to Elements
        logo.setAnimation(bottomAnim);
        title.setAnimation(topAnim);
        tagline.setAnimation(topAnim);

        // Delay splash screen
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);



    }
}