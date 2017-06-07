package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.UserProfile;
import com.now.fitness.nowfitnessui.R;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    //Splash Screen Timer
    private static int SPLASH_TIMER = 2000;
    public static Database mDb;
    private Intent intent;
    int duration = Toast.LENGTH_SHORT;
    private String firstname;
    private String lastname;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        Log.i("splash screen activity", "value: " + uiOptions);
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);

        mDb = new Database(this);
        mDb.open();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Check if user already exists in the database or not
                //If it does not exist open UserProfileActivity else open MainActivity
                try{
                    List<UserProfile> usr = Database.mUserDAL.findAll();

                    if(usr.isEmpty()) {
                        Intent intent = new Intent(SplashActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                    }else{
                        UserProfile user = usr.get(0);
                        firstname = user.getFirstname();
                        lastname = user.getLastname();
                        userId = String.valueOf(user.getUserId());

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("firstname", firstname);
                        intent.putExtra("lastname", lastname);
                        intent.putExtra("userId", userId);

                        startActivity(intent);
                    }
                }catch (Exception ex){
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: Unable to Connect to the database.", duration);
                    toast.show();
                }

                finish();
            }
        }, SPLASH_TIMER);

    }
        @Override
        protected void onStop() {
            mDb.close();
            super.onStop();
        }
    }



