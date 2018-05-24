package com.example.nikit.eventsapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nikit.eventsapp.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private SharedPreferencesUtil sharedPreferencesUtil;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_events:
                    toolbar.setTitle("Events");
                    fragment = new EventsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);

                    return true;
                case R.id.navigation_title1:
                    toolbar.setTitle("Title1");
                    return true;
                case R.id.navigation_title2:
                    toolbar.setTitle("Title2");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = getSupportActionBar();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.hide();
        getSupportActionBar().setTitle("Events");
        EventsFragment eventsFragment = new EventsFragment();
        loadFragment(eventsFragment);


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
