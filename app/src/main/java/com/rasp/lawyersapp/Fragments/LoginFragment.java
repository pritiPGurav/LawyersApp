package com.rasp.lawyersapp.Fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.utility.FragmentTransition;
import com.rasp.lawyersapp.view.TextViewCustomize;

public class LoginFragment extends Fragment implements View.OnClickListener {
    TextViewCustomize txt_sign_up;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        txt_sign_up = (TextViewCustomize) v.findViewById(R.id.txt_sign_up);
        txt_sign_up.setTypeface(Typeface.DEFAULT_BOLD);
        txt_sign_up.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.txt_sign_up:
                FragmentTransition.showFragmentAnimationWithBackFlip(getActivity(), new OTPFragment(), R.id.container, null, null);
                break;
        }
    }
}
