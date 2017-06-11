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

import com.now.fitness.nowfitnessui.DAL.MyWorkoutDAL;
import com.now.fitness.nowfitnessui.DAL.MyWorkoutPlanDAL;
import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.R;

import java.util.List;

public class CreateMyWorkoutActivity extends AppCompatActivity {

    MyWorkoutPlan myWorkoutPlan;
    MyWorkoutPlanDAL myWorkoutPlanDAL;
    MyWorkout myWorkout;
    MyWorkoutDAL myWorkoutDAL;
    EditText mWorkoutName, mNumberOfWorkouts;
    Button mButton;

    public static Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_workout);

        myWorkoutPlan = new MyWorkoutPlan();
        myWorkout = new MyWorkout();
        mWorkoutName = (EditText) findViewById(R.id.editText_WorkoutName);
        mNumberOfWorkouts = (EditText) findViewById(R.id.editText_NumberOfWorkouts);
        mButton = (Button) findViewById(R.id.button_CreateWorkoutPlan);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get values of EditText
                String planName = mWorkoutName.getText().toString();
                int numberOfWorkouts = Integer.parseInt(mNumberOfWorkouts.getText().toString());

                //Create and open new Database
                mDb = new Database(CreateMyWorkoutActivity.this);

                mDb.open();

                //Store values to entities of myWorkoutPlan
                myWorkoutPlan.setMyWorkoutPlanName(planName);
                myWorkoutPlan.setNumberOfWorkouts(numberOfWorkouts);

                //Call DAL Object
                boolean insertWorkoutPlan = Database.mMyWorkoutPlanDAL.insertMyWorkoutPlan(myWorkoutPlan);

                mDb.close();



                mDb.open();
                int myWorkoutPlanId = Database.mMyWorkoutPlanDAL.findAll().size() - 1;

                for (int i = 0; i < numberOfWorkouts; i++) {
                    //Store values to entities of myWorkout
                    myWorkout.setMyWorkoutName(planName + " " + (i + 1));
                    Log.d("Workout Name:", planName + " " + (i + 1));
                    myWorkout.setMyWorkoutPlanId(myWorkoutPlanId + 1);
                    Log.d("Workout Plan Id:", String.valueOf(myWorkoutPlanId) + 1);

                    Database.mMyWorkoutDAL.insertMyWorkout(myWorkout);
                }

                mDb.close();
                finish();

            }
        });
    }
}
