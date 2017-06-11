package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.database.Cursor;
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
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;
import com.now.fitness.nowfitnessui.Object.WorkoutList;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListActivity extends AppCompatActivity {

    private ListView mListView;
    Database mDb;
    MyWorkoutRoutine myWorkoutRoutine;
    private int myWorkoutPlanId;
    private int myWorkoutId;
    private int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        myWorkoutRoutine = new MyWorkoutRoutine();
        mListView = (ListView) findViewById(R.id.listView_WorkoutList);

        getWorkoutList();
    }

    public void getWorkoutList() {
        try {
            mDb = new Database(WorkoutListActivity.this);
            mDb.open();

            List<WorkoutList> workoutList = Database.mWorkoutListDAL.findAll();
            final ArrayList<String> idList = new ArrayList<>();
            ArrayList<String> nameList = new ArrayList<>();

            for (int i = 0; i < workoutList.size(); i++) {
                idList.add(String.valueOf(workoutList.get(i).getWorkoutId()));
                nameList.add(workoutList.get(i).getExerciseName());
//                nameList.add(String.valueOf(workoutList.get(i).getWorkoutId()));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
                mListView.setAdapter(listAdapter);
            }

            mDb.close();

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                    //get the intent extra from the ListDataActivity
                    Intent receivedIntent = getIntent();

                    //now get the itemID we passed as an extra
                    myWorkoutPlanId = receivedIntent.getIntExtra("MyWorkoutPlanId", -1);
                    myWorkoutId = receivedIntent.getIntExtra("MyWorkoutId", -1);
                    workoutId = Integer.parseInt(idList.get(i));

//                    Log.d("Workout Plan Id:", String.valueOf(myWorkoutPlanId));
//                    Log.d("MyWorkout Id:", String.valueOf(myWorkoutId));
//                    Log.d("Workout Id:", String.valueOf(workoutId));

                    mDb.open();
                    myWorkoutRoutine.setMyWorkoutPlanId(myWorkoutPlanId);
                    myWorkoutRoutine.setMyWorkoutId(myWorkoutId);
                    myWorkoutRoutine.setWorkoutId(workoutId);

                    Log.d("Workout Plan Id:", String.valueOf(myWorkoutRoutine.getMyWorkoutPlanId()));
                    Log.d("MyWorkout Id:", String.valueOf(myWorkoutRoutine.getMyWorkoutId()));
                    Log.d("Workout Id:", String.valueOf(myWorkoutRoutine.getWorkoutId()));

                    boolean insertWorkoutRoutine = Database.mMyWorkoutRoutineDAL.insertMyWorkoutRoutine(myWorkoutRoutine);
                    if (insertWorkoutRoutine) {
                        Toast.makeText(WorkoutListActivity.this, "Workout Added", Toast.LENGTH_LONG).show();
                    }
                    mDb.close();
                    finish();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
