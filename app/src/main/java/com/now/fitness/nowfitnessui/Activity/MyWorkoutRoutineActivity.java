package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;
import com.now.fitness.nowfitnessui.Object.WorkoutList;
import com.now.fitness.nowfitnessui.R;

import java.util.List;

public class MyWorkoutRoutineActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton;

    private int myWorkoutPlanId;
    private int myWorkoutId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_routine);

        mListView = (ListView) findViewById(R.id.listView_MyWorkoutRoutine);
        mButton = (Button) findViewById(R.id.button_AddWorkoutRoutine);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        myWorkoutPlanId = receivedIntent.getIntExtra("MyWorkoutPlanId",-1);
        myWorkoutId = receivedIntent.getIntExtra("MyWorkoutId",-1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(MyWorkoutRoutineActivity.this, "MyWorkout ID " + String.valueOf(myWorkoutId), Toast.LENGTH_LONG).show();
                Intent WorkoutListActivityIntent = new Intent(MyWorkoutRoutineActivity.this, WorkoutListActivity.class);

                WorkoutListActivityIntent.putExtra("xMyWorkoutPlanId:", myWorkoutPlanId);
                Log.i("Workout Plan Id:", String.valueOf(myWorkoutPlanId));
                WorkoutListActivityIntent.putExtra("xMyWorkoutId:", myWorkoutId);
                Log.i("Workout Id:", String.valueOf(myWorkoutId));
                startActivity(WorkoutListActivityIntent);
            }
        });

    }
}
