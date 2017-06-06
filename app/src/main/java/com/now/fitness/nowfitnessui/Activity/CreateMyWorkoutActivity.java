package com.now.fitness.nowfitnessui.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.DAL.MyWorkoutPlanDAL;
import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.R;

import java.util.List;

public class CreateMyWorkoutActivity extends AppCompatActivity {

    MyWorkoutPlan myWorkoutPlan;
    MyWorkoutPlanDAL myWorkoutPlanDAL;
    EditText mWorkoutName, mNumberOfWorkouts;
    Button mButton;

    public static Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_workout);

        myWorkoutPlan = new MyWorkoutPlan();

        mWorkoutName = (EditText) findViewById(R.id.editText_WorkoutName);
        mNumberOfWorkouts = (EditText) findViewById(R.id.editText_NumberOfWorkouts);
        mButton = (Button) findViewById(R.id.button_CreateWorkoutPlan);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get values of EditText
                String planName = mWorkoutName.getText().toString();
                int numberOfWorkouts = Integer.parseInt(mNumberOfWorkouts.getText().toString());

                //Store values to entities
                myWorkoutPlan.setMyWorkoutPlanName(planName);
                myWorkoutPlan.setNumberOfWorkouts(numberOfWorkouts);

                //Create and open new Database
                mDb = new Database(CreateMyWorkoutActivity.this);
                mDb.open();

                //Call DAL Object
                boolean insertData = Database.mMyWorkoutPlanDAL.insertMyWorkoutPlan(myWorkoutPlan);

                if (insertData) {
                    Toast.makeText(CreateMyWorkoutActivity.this, "Workout Plan Added", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(CreateMyWorkoutActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                }
                mDb.close();
            }
        });

    }
}
