package com.rasp.lawyersapp.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rasp.lawyersapp.Fragments.LoginFragment;
import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.utility.FragmentTransition;
import com.rasp.lawyersapp.view.TextViewCustomize;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FragmentTransition.showFragmentAnimationWithBackFlip(this, new LoginFragment(), R.id.container, null, null);

    }
}
