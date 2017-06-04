package com.now.fitness.nowfitnessui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.now.fitness.nowfitnessui.Model.RssFeedReader;
import com.now.fitness.nowfitnessui.R;

public class NewsFragment extends Fragment {

    private View mView;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_news, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mSwipeLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RssFeedReader rssFeedReader = new RssFeedReader(getActivity(), mRecyclerView, mSwipeLayout);
        rssFeedReader.execute();

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                RssFeedReader rssFeedReader = new RssFeedReader(getActivity(), mRecyclerView, mSwipeLayout);
                rssFeedReader.execute();
            }
        });

        return mView;
    }


}