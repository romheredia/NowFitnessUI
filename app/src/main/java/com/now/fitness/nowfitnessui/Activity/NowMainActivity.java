package com.now.fitness.nowfitnessui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;

/**
 * This class is the Main Activity for the Name Your Workout functionality
 * @author Maycor Gerilla on 5/26/2017.
 */

public class NowMainActivity extends AppCompatActivity {
    static private final String TAG = "NOWFitness-UI";
    private TextView textViewName;
    private LinearLayout linearLayout;

    /**
    * Creates the View for the NowMainActivity
     * @param savedInstanceState
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_main);

        ArrayList<String> generated = getIntent().getStringArrayListExtra("workoutGen");
        String name = getIntent().getStringExtra("username");
        Log.i(TAG,"size: "+generated.size());


        textViewName = (TextView) findViewById(R.id.textView);
        textViewName.append(name.toUpperCase());

        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutNOW);

        //populate textView with values from ArrayList
        for(int i=0; i<generated.size(); i=i+2) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(generated.get(i)+"         Repetitions: "+generated.get(i+1));
            linearLayout.addView(textView);
        }

    }
}
