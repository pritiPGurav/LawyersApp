package com.rasp.lawyersapp.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rasp.lawyersapp.Fragments.LawyersDetailsFragment;
import com.rasp.lawyersapp.Fragments.UserProfileFragment;
import com.rasp.lawyersapp.MainActivity;
import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.utility.AppDelegate;
import com.rasp.lawyersapp.utility.FragmentTransition;
import com.rasp.lawyersapp.view.ToolbarCustomization;

public class LawyersDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyers_details);
        initView();
        FragmentTransition.showFragmentAnimationWithBackFlip(this, new UserProfileFragment(), R.id.container, null, null);
    }

    private void initView() {
        ToolbarCustomization toolbar = (ToolbarCustomization) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setup("Lawyer Details");
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorwhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

      //  final Drawable upArrow = getResources().getDrawable(R.drawable.back_arrow);
      //  assert upArrow != null;
      //  upArrow.setColorFilter(getResources().getColor(R.color.colorwhite), PorterDuff.Mode.SRC_ATOP);
      //  getSupportActionBar().setHomeAsUpIndicator(upArrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LawyersDetailsFragment lawyersDetailsFragment = (LawyersDetailsFragment) getFragmentManager().findFragmentByTag("Lawyer Details");
                if (lawyersDetailsFragment != null && lawyersDetailsFragment.isVisible()) {
                    AppDelegate.hideKeyBoard(LawyersDetails.this);
                    getFragmentManager().popBackStack();
                    getFragmentManager().popBackStack();
                } else {
                    closeActivity();
                }
            }
        });
    }

    private void closeActivity() {
        //startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        closeActivity();
    }
    @Override
    public void onStop() {
        super.onStop();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
