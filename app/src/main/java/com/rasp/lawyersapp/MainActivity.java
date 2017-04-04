package com.rasp.lawyersapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.rasp.lawyersapp.Activities.BaseActivity;
import com.rasp.lawyersapp.Fragments.CategoryFavouriteTabFragment;
import com.rasp.lawyersapp.Fragments.CategoryFragment;
import com.rasp.lawyersapp.Fragments.KnowFragment;
import com.rasp.lawyersapp.Fragments.MeFragment;
import com.rasp.lawyersapp.Fragments.UserProfileFragment;
import com.rasp.lawyersapp.utility.AppDelegate;
import com.rasp.lawyersapp.view.ToolbarCustomization;
import com.newrelic.agent.android.NewRelic;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottom_navigation;
    public static int frag_index = 0;
    ToolbarCustomization toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewRelic.withApplicationToken(AppDelegate.newrelicAppKey).start(this.getApplication());
        setContentView(R.layout.activity_main);
        initView();
        changeFragment(0);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_know:
                        toolbar.setup("Know");
                        changeFragment(0);
                        break;
                    case R.id.action_find:
                        toolbar.setup("Find");
                        changeFragment(1);
                        break;
                    case R.id.action_me:
                        toolbar.setup("Me");
                        changeFragment(2);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void setViewProperties() {

    }

    /**
     * To load fragments for sample
     *
     * @param position menu index
     */
    private void changeFragment(int position) {

        Fragment newFragment = null;

        if (position == 0) {
            newFragment = new KnowFragment();
        } else if (position % 2 != 0) {
            newFragment = new CategoryFavouriteTabFragment();
        } else {
            newFragment = new MeFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, newFragment).addToBackStack(null).commit();

    }


    private void initView() {
        toolbar = (ToolbarCustomization) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setup("Know");
    }


    public void closeActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {

        if (frag_index == 0) {
            closeActivity();
        } else {
            int count = getFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getFragmentManager().popBackStack();
            }
        }
    }



}
