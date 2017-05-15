package com.now.fitness.nowfitnessui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GenerateFragment extends Fragment {

    private Context context;
    private Button button;

    public GenerateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generate, container, false);
        context = view.getContext();

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MyWorkoutActivity.class));
            }
        });


        return inflater.inflate(R.layout.fragment_generate, container, false);
    }

//    public void onClick_Button(View view) {
//        startActivity(new Intent(getActivity(), MyWorkoutActivity.class));
//    }


}
