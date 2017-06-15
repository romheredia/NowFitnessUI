package com.now.fitness.nowfitnessui.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;
import com.now.fitness.nowfitnessui.Object.WorkoutList;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is MyWorkoutActivity is for listing My Workout list created after creating a workout plan.
 *
 * @author Romeric Heredia
 */

public class MyWorkoutRoutineActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ListView mListView;
    private Button mButton, mButtonEdit;

    private String myWorkoutRoutineName;
    private int myWorkoutPlanId;
    private int myWorkoutId;

    private boolean refreshOnResume = false;
    Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_routine);


        mToolbar = (Toolbar) findViewById(R.id.toolbar_MyWorkout);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);


        mListView = (ListView) findViewById(R.id.listView_MyWorkoutRoutine);
        mButton = (Button) findViewById(R.id.button_AddWorkoutRoutine);
        mButtonEdit = (Button) findViewById(R.id.button_EditWorkoutRoutine);


        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        myWorkoutPlanId = receivedIntent.getIntExtra("MyWorkoutPlanId", -1);
        myWorkoutId = receivedIntent.getIntExtra("MyWorkoutId", -1);

        mDb = new Database(MyWorkoutRoutineActivity.this);
        mDb.open();

        MyWorkout myWorkout = Database.mMyWorkoutDAL.findByMyWorkoutId(myWorkoutId);
        myWorkoutRoutineName = myWorkout.getMyWorkoutName();

        setTitle(myWorkoutRoutineName);
//        mEditText.setText();
        mDb.close();


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

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent WorkoutListActivityIntent = new Intent(MyWorkoutRoutineActivity.this, MyWorkoutRoutineEditActivity.class);

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
            try {
                mDb = new Database(MyWorkoutRoutineActivity.this);
                mDb.open();

                MyWorkout myWorkout = Database.mMyWorkoutDAL.findByMyWorkoutId(myWorkoutId);
                myWorkoutRoutineName = myWorkout.getMyWorkoutName();

                setTitle(myWorkoutRoutineName);
//        mEditText.setText();
                mDb.close();
            } catch (Exception e) {

            }
            getWorkoutRoutineList();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshOnResume = true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the list for Workout Routine
     */
    public void getWorkoutRoutineList() {
        mDb = new Database(MyWorkoutRoutineActivity.this);
        mDb.open();

        try {
            final List<MyWorkoutRoutine> myWorkoutRoutineList = Database.mMyWorkoutRoutineDAL.findMyWorkoutRoutine(myWorkoutPlanId, myWorkoutId);
            List<WorkoutList> workoutList = Database.mWorkoutListDAL.findAll();
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

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    int workoutRoutineId = myWorkoutRoutineList.get(i).getMyWorkoutRoutineId();

//                    Toast.makeText(MyWorkoutRoutineActivity.this, String.valueOf(WorkoutId), Toast.LENGTH_LONG).show();

                    try {
                        mDb.open();
                        boolean deleteWorkout = Database.mMyWorkoutRoutineDAL.deleteByMyWorkoutRoutineId(workoutRoutineId);
                        if (deleteWorkout) {
                            Toast.makeText(MyWorkoutRoutineActivity.this, "Workout Deleted", Toast.LENGTH_LONG).show();
                        }
                        mDb.close();
                    } catch (Exception e) {

                    }
                    getWorkoutRoutineList();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        mDb.close();
    }
}
