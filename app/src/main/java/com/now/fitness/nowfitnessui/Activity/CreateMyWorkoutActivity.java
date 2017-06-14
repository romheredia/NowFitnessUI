package com.now.fitness.nowfitnessui.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.DAL.MyWorkoutDAL;
import com.now.fitness.nowfitnessui.DAL.MyWorkoutPlanDAL;
import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.Object.UserProfile;
import com.now.fitness.nowfitnessui.R;

import java.util.List;

import static android.R.attr.duration;


/**
 * This class is the data access layer for the UserProfile Model which imp
 * @author  Romeric Heredia
 */

public class CreateMyWorkoutActivity extends AppCompatActivity {

    MyWorkoutPlan myWorkoutPlan;
    MyWorkout myWorkout;
    EditText mWorkoutName, mNumberOfWorkouts;
    Button mButton;
    private Toolbar mToolbar;
    private ActionBar mActionBar;

    public static Database mDb;
    int numberOfWorkouts;

    /**
     * Creates the view for CreateMyWorkoutActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create_my_workout);
        setTitle("Create Workout Plan");

        mToolbar = (Toolbar) findViewById(R.id.toolbar_CreateMyWorkout);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        myWorkoutPlan = new MyWorkoutPlan();
        myWorkout = new MyWorkout();
        mWorkoutName = (EditText) findViewById(R.id.editText_WorkoutName);
        mNumberOfWorkouts = (EditText) findViewById(R.id.editText_NumberOfWorkouts);
        mButton = (Button) findViewById(R.id.button_CreateWorkoutPlan);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                //Get values of EditText
                String planName = mWorkoutName.getText().toString();
//

                //perform validation for required fields workout plan name and numberof workouts
                if (mWorkoutName.getText().toString().length() == 0) {
                    mWorkoutName.setError("Workout Name is required!");
                    flag++;
                }

                //perform validation for height and weight input
                try {
                    numberOfWorkouts = Integer.parseInt(mNumberOfWorkouts.getText().toString());
                } catch (NumberFormatException e) {
                    mNumberOfWorkouts.setError("Incorrect input!");
                    flag++;
                }

                //Passed the validation
                if (flag == 0) {
                    //Store values to entities of myWorkoutPlan
                    myWorkoutPlan.setMyWorkoutPlanName(planName);
                    myWorkoutPlan.setNumberOfWorkouts(numberOfWorkouts);


                    try {
                        //Create and open new Database


                        mDb = new Database(CreateMyWorkoutActivity.this);

                        mDb.open();


                        //Call DAL Object
                        boolean insertWorkoutPlan = Database.mMyWorkoutPlanDAL.insertMyWorkoutPlan(myWorkoutPlan);

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
                    } catch (Exception e) {
                        Toast.makeText(CreateMyWorkoutActivity.this, R.string.prompt_success_insert, Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
