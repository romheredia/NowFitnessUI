package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.WorkoutList;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Maycor Gerilla on 5/26/2017.
 *
 * This class will generate workout from the database based on the supplied user name.
 */

public class NowActivity extends AppCompatActivity {
    static private final String TAG = "NOWFitness-UI";
    public static Database mDb;

    private EditText editTextName;
    int duration = Toast.LENGTH_LONG;
    private List<String> workoutGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now);

        //create database
        mDb = new Database(this);
        mDb.open();

        editTextName = (EditText) findViewById(R.id.editTextInputName);
        workoutGen = new ArrayList<String>();
    }

    public void onClick_NOWorkout(View v) {
        String inputName = editTextName.getText().toString();
        if (editTextName.getText().toString().length() == 0) {
            editTextName.setError("Input name is required!");
        }else if(!Pattern.matches("[a-zA-Z]+", inputName)) {
            editTextName.setError("Incorrect input!");
        }else if(inputName.length()>25){
                editTextName.setError("Input exceeds maximum length of 25 characters!");
        }else{

            try {
                List<WorkoutList> wrk = Database.mWorkoutListDAL.findByName(inputName.trim());
                Intent intent = new Intent(NowActivity.this, NowMainActivity.class);

                if (!wrk.isEmpty()) {
                    Log.i(TAG, "list size: " + wrk.size());

                    for (WorkoutList work : wrk) {
                        workoutGen.add(work.getExerciseName());
                        workoutGen.add(String.valueOf(work.getRepetitions()));
                    }

                    intent.putStringArrayListExtra("workoutGen", (ArrayList<String>) workoutGen);
                    intent.putExtra("username", inputName);
                    startActivity(intent);
                }

            } catch (Exception ex) {
                Toast toast = Toast.makeText(this, "Error: Unable to Connect to the database." + ex.getMessage(), duration);
                toast.show();
            }

            finish();
            mDb.close();
        }
    }

}

