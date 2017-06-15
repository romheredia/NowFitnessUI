package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.R;

public class MyWorkoutRoutineEditActivity extends AppCompatActivity {

    private int myWorkoutId;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private EditText mEditText;

    private Button mButton;

    Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_routine_edit);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_EditMyWorkoutRoutine);
        setSupportActionBar(mToolbar);
        setTitle("Edit Workout Routine Name");

        mActionBar = getSupportActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        mEditText = (EditText) findViewById(R.id.editText_MyWorkoutRoutineName);
        mButton = (Button) findViewById(R.id.button_EditMyWorkoutRoutineName);
        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        myWorkoutId = receivedIntent.getIntExtra("MyWorkoutId", -1);

        mDb = new Database(MyWorkoutRoutineEditActivity.this);
        mDb.open();

        MyWorkout myWorkout = Database.mMyWorkoutDAL.findByMyWorkoutId(myWorkoutId);
        mEditText.setText(myWorkout.getMyWorkoutName());
        mDb.close();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flag = 0;
                //Get values of EditText
                String routineName = mEditText.getText().toString();
//

                //perform validation for required fields workout plan name and numberof workouts
                if (mEditText.getText().toString().length() == 0) {
                    mEditText.setError("Workout Name is required!");
                    flag++;
                }

                //Passed the validation
                if (flag == 0) {
                    //Store values to entities of myWorkoutPlan
                    try {
                        mDb.open();

                        boolean editMyWorkoutRoutineName = Database.mMyWorkoutDAL.updateMyWorkoutName(myWorkoutId, mEditText.getText().toString());
                        Toast.makeText(MyWorkoutRoutineEditActivity.this, "Workout Name Updated", Toast.LENGTH_LONG).show();
                        mDb.close();

                        finish();
                    } catch (Exception e) {
                        Toast.makeText(MyWorkoutRoutineEditActivity.this, R.string.prompt_error, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
