package com.rasp.lawyersapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rasp.lawyersapp.MainActivity;
import com.rasp.lawyersapp.R;

public class MeFragment extends Fragment {
    private TextView txt1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_know, container, false);
        initView(view);
        return view;
    }
    private void initView(@NonNull View view) {
        txt1 = (TextView)view.findViewById(R.id.txt1);
        txt1.setText("ME Fragment....");
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.frag_index = 0;
    }
}
