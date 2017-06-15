package com.now.fitness.nowfitnessui.Activity;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class is MyWorkoutActivity is for listing My Workout list created after creating a workout plan.
 * @author Romeric Heredia
 *
 *
 * */

public class  MyWorkoutActivity extends AppCompatActivity {

    private ListView mListView;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    Database mDb;

    private boolean refreshOnResume = false;
    /**
     * Creates the view for MyWorkoutActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);
        setTitle("My Workout");

        Intent receivedIntent = getIntent();

        mListView = (ListView) findViewById(R.id.listView_MyWorkoutList);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_MyWorkout);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        getMyWorkoutList(receivedIntent.getIntExtra("MyWorkoutPlanId", -1));

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (refreshOnResume) {
            Intent receivedIntent = getIntent();
            getMyWorkoutList(receivedIntent.getIntExtra("MyWorkoutPlanId", -1));
        } else {
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
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the list for Workoutlist
     * @param myWorkoutPlanId
     */
    public void getMyWorkoutList(final int myWorkoutPlanId) {
        try {
            mDb = new Database(this);
            mDb.open();

            final List<MyWorkout> myWorkoutList = Database.mMyWorkoutDAL.findByMyWorkoutPlanId(myWorkoutPlanId);
            ArrayList<String> nameList = new ArrayList<>();

            for (int i = 0; i < myWorkoutList.size(); i++) {
                String idName = String.valueOf(myWorkoutList.get(i).getMyWorkoutId()) + " " + myWorkoutList.get(i).getMyWorkoutName().toString();
                String myWorkoutName = myWorkoutList.get(i).getMyWorkoutName();
                nameList.add(myWorkoutName);

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
                mListView.setAdapter(listAdapter);
            }

            mDb.close();

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    int myWorkoutId = myWorkoutList.get(i).getMyWorkoutId();
                    String myWorkoutRoutineName = myWorkoutList.get(i).getMyWorkoutName();

                    Intent myWorkoutActivityIntent = new Intent(MyWorkoutActivity.this, MyWorkoutRoutineActivity.class);

                    myWorkoutActivityIntent.putExtra("MyWorkoutRoutineName", myWorkoutRoutineName);
                    myWorkoutActivityIntent.putExtra("MyWorkoutPlanId", myWorkoutPlanId);
                    myWorkoutActivityIntent.putExtra("MyWorkoutId", myWorkoutId);
                    Log.i("Workout Plan Id:", String.valueOf(myWorkoutPlanId));
                    Log.i("My Workout Id:", String.valueOf(myWorkoutId));

//                    Toast.makeText(MyWorkoutActivity.this, String.valueOf(myWorkoutId), Toast.LENGTH_LONG).show();
                    startActivity(myWorkoutActivityIntent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
