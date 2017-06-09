package com.now.fitness.nowfitnessui.Activity;

import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        int size = generated.size()/2;

        TextView textViewWork[] = new TextView[generated.size()];
        TextView textViewReps[] = new TextView[generated.size()];

        //populate workout name
        for(int i=0, x=0; i<generated.size(); i=i+2, x++){
            textViewWork[x] = new TextView(this);
            textViewWork[x].setText(generated.get(i));
            TextViewCompat.setTextAppearance(textViewWork[x], R.style.TextAppearance_AppCompat_Large);
        }

        //populate workout repetitions
        for(int i=0, x=0; i<generated.size(); i=i+2, x++) {
            textViewReps[x] = new TextView(this);
            textViewReps[x].setText(R.string.text_view_reps+""+generated.get(i+1));
            TextViewCompat.setTextAppearance(textViewReps[x], R.style.TextAppearance_AppCompat_Large);
        }

            //populate textView with values from ArrayList
        for(int i=0; i<size; i++) {
            TextView blankText = new TextView(this);
            blankText.setText(" ");

            linearLayout.addView(textViewWork[i]);
            linearLayout.addView(textViewReps[i]);
            linearLayout.addView(blankText);
        }

    }

    public void onClick_DoneWorkout(View view){
        Toast.makeText(this, R.string.prompt_done, Toast.LENGTH_SHORT).show();
        finish();
    }

}
