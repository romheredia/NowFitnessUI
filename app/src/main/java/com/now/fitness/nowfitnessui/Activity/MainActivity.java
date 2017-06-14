package com.now.fitness.nowfitnessui.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.now.fitness.nowfitnessui.Fragment.NewsFragment;
import com.now.fitness.nowfitnessui.Fragment.MyWorkoutFragment;
import com.now.fitness.nowfitnessui.Fragment.NowFragment;
import com.now.fitness.nowfitnessui.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class is the Main Activity of NOW Fitness application
 * @author Romeric Heredia
 *
 *
 * */

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ViewPager mViewPager;
    static private final String TAG = "NOWFitness-UI";
    private String fname;
    private String lname;
    private String userId;


    /**
     * Creates the view for MainActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.toolbar_Main);
        setSupportActionBar(mToolBar);

        mViewPager = (ViewPager) findViewById(R.id.viewPager_Main);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_Main);
        tabLayout.setupWithViewPager(mViewPager);

        fname = getIntent().getStringExtra("firstname");
        lname = getIntent().getStringExtra("lastname");
        userId = getIntent().getStringExtra("userId");

    }

    /**
     * Creates the Menu Option
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Loads the User Profile Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_UserProfile:{
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                intent.putExtra("firstname", fname);
                intent.putExtra("lastname", lname);
                intent.putExtra("userId", userId);

                startActivity(intent);
            }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *  Sets the View Pager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new MyWorkoutFragment(), "My Workout");
        adapter.addFragment(new NowFragment(), "NOW");
        viewPager.setAdapter(adapter);
    }

    /**
     * This class is the View Page Adapter
     */
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public  int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
