package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;
import com.now.fitness.nowfitnessui.Object.WorkoutList;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

public class MyWorkoutRoutineActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton;

    private int myWorkoutPlanId;
    private int myWorkoutId;

    private boolean refreshOnResume = false;
    Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_routine);

        mListView = (ListView) findViewById(R.id.listView_MyWorkoutRoutine);
        mButton = (Button) findViewById(R.id.button_AddWorkoutRoutine);


        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        myWorkoutPlanId = receivedIntent.getIntExtra("MyWorkoutPlanId", -1);
        myWorkoutId = receivedIntent.getIntExtra("MyWorkoutId", -1);

        getWorkoutRoutineList();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int myWorkoutId = myWorkoutRoutineList.get(i).getMyWorkoutId();
//                Toast.makeText(MyWorkoutRoutineActivity.this, myWorkoutId, Toast.LENGTH_LONG).show();
                Intent WorkoutListActivityIntent = new Intent(MyWorkoutRoutineActivity.this, WorkoutListActivity.class);

                WorkoutListActivityIntent.putExtra("MyWorkoutPlanId", myWorkoutPlanId);
                WorkoutListActivityIntent.putExtra("MyWorkoutId", myWorkoutId);

                Log.i("Workout Plan Id:", String.valueOf(myWorkoutPlanId));
                Log.i("My Workout Id:", String.valueOf(myWorkoutId));

                startActivity(WorkoutListActivityIntent);
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (refreshOnResume) {
            getWorkoutRoutineList();
        } else {
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshOnResume = true;
    }

    public void getWorkoutRoutineList() {
        mDb = new Database(MyWorkoutRoutineActivity.this);
        mDb.open();

        try {
            List<MyWorkoutRoutine> myWorkoutRoutineList = Database.mMyWorkoutRoutineDAL.findMyWorkoutRoutine(myWorkoutPlanId, myWorkoutId);
            List<WorkoutList> workoutList  = Database.mWorkoutListDAL.findAll();
            ArrayList<String> idList = new ArrayList<>();
            ArrayList<String> nameList = new ArrayList<>();

            for (int i = 0; i < myWorkoutRoutineList.size(); i++) {
                String workoutId = String.valueOf(myWorkoutRoutineList.get(i).getWorkoutId());

                String idName = String.valueOf(workoutId);
                String workoutName = myWorkoutRoutineList.get(i).getWorkoutName();

                nameList.add(workoutName);

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
                mListView.setAdapter(listAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDb.close();
    }
}
