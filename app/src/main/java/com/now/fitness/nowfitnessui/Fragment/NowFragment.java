package com.now.fitness.nowfitnessui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.now.fitness.nowfitnessui.Activity.NowActivity;
import com.now.fitness.nowfitnessui.R;

import static android.R.attr.button;

/**
 * This class is the fragment for Now Tab
 * @author
 */
public class NowFragment extends Fragment {

    private View mView;
    private Context context;
    private Button button;

    public NowFragment() {
        // Required empty public constructor
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    /**
     * Creates the view for the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_now, container, false);
        context = mView.getContext();

        //calls the Name Your Workout activity
        button = (Button) mView.findViewById(R.id.NowWorkout);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NowActivity.class));
            }
        });

        return mView;
    }

}
