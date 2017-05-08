package com.now.fitness.nowfitnessui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void onClick_UserProfileSave(View view) {
        startActivity(new Intent(UserProfileActivity.this, MainActivity.class));
        finish();
    }
}
