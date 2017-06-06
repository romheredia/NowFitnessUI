package com.now.fitness.nowfitnessui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Activity.CreateMyWorkoutActivity;
import com.now.fitness.nowfitnessui.Model.Database;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

public class MyWorkoutFragment extends Fragment {

    private View mView;
    private ListView mListView;
    private Button mButton;

    private boolean refreshOnResume = false;
    Database mDb;

    public MyWorkoutFragment() {
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
        mView = inflater.inflate(R.layout.fragment_my_workout, container, false);
        mListView = (ListView) mView.findViewById(R.id.listView_MyWorkoutPlanList);
        mButton = (Button) mView.findViewById(R.id.button_AddWorkoutPlan);

        getMyWorkoutPlanList();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateMyWorkoutActivity.class));
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (refreshOnResume) {
            getMyWorkoutPlanList();
        } else {
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshOnResume = true;
    }

    public void getMyWorkoutPlanList(){
        try {
            mDb = new Database(getActivity());
            mDb.open();

            List<MyWorkoutPlan> myWorkoutPlanList = Database.mMyWorkoutPlanDAL.findAll();
            ArrayList<String> nameList = new ArrayList<>();

            for (int i = 0; i < myWorkoutPlanList.size(); i++) {
                nameList.add(myWorkoutPlanList.get(i).getMyWorkoutPlanName().toString());

                ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, nameList);
                mListView.setAdapter(listAdapter);
            }

            mDb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
