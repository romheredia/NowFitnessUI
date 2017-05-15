package com.now.fitness.nowfitnessui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {

    //Splash Screen Timer
    private static int SPLASH_TIMER = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        Log.i("splash screen activity","value: "+uiOptions);
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, UserProfileActivity.class));
                finish();
            }
        }, SPLASH_TIMER);
    }
}


