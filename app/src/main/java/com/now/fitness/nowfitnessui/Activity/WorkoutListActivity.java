package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.WorkoutList;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListActivity extends AppCompatActivity {

    private ListView mListView;
    Database mDb;

    private int myWorkoutPlanId;
    private int myWorkoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        mListView = (ListView) findViewById(R.id.listView_WorkoutList);

        getWorkoutList();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                //get the intent extra from the ListDataActivity
                Intent receivedIntent = getIntent();

                //now get the itemID we passed as an extra
                myWorkoutPlanId = receivedIntent.getIntExtra("xMyWorkoutPlanId",-1);
                myWorkoutId = receivedIntent.getIntExtra("xMyWorkoutId",-1);

                Log.d("Workout Plan Id:", String.valueOf(myWorkoutPlanId));
                Log.d("Workout Id:", String.valueOf(myWorkoutId));
            }
        });
    }

    public void getWorkoutList() {
        try {
            mDb = new Database(this);
            mDb.open();

            List<WorkoutList> workoutList = Database.mWorkoutListDAL.findAll();
            ArrayList<String> nameList = new ArrayList<>();

            for (int i = 0; i < workoutList.size(); i++) {
                nameList.add(String.valueOf(workoutList.get(i).getExerciseName()));

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
                mListView.setAdapter(listAdapter);
            }

            mDb.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
