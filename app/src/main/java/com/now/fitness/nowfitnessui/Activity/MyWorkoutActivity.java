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
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

public class  MyWorkoutActivity extends AppCompatActivity {

    private ListView mListView;
    Database mDb;

    private int myWorkoutPlanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);

        Intent receivedIntent = getIntent();

        mListView = (ListView) findViewById(R.id.listView_MyWorkoutList);

        getMyWorkoutList(receivedIntent.getIntExtra("MyWorkoutPlanId", -1));

    }

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

                    Intent myWorkoutActivityIntent = new Intent(MyWorkoutActivity.this, MyWorkoutRoutineActivity.class);

                    myWorkoutActivityIntent.putExtra("MyWorkoutPlanId", myWorkoutPlanId);
                    myWorkoutActivityIntent.putExtra("MyWorkoutId", myWorkoutId);
                    Log.i("Workout Plan Id:", String.valueOf(myWorkoutPlanId));
                    Log.i("My Workout Id:", String.valueOf(myWorkoutId));

                    Toast.makeText(MyWorkoutActivity.this, String.valueOf(myWorkoutId), Toast.LENGTH_LONG).show();
                    startActivity(myWorkoutActivityIntent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
