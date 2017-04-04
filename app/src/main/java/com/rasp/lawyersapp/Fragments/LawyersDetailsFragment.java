package com.rasp.lawyersapp.Fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.rasp.lawyersapp.Constants.MarshmallowPermissionsCheck;
import com.rasp.lawyersapp.R;
public class LawyersDetailsFragment extends Fragment implements View.OnClickListener {

    RatingBar ratingBar;
    Button btn_call;
    MarshmallowPermissionsCheck marshmallowPermissionsCheck;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        marshmallowPermissionsCheck = new MarshmallowPermissionsCheck(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_lawyers_details, container, false);
        btn_call = (Button) v.findViewById(R.id.btn_call);
        btn_call.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId())
        {
            case R.id.btn_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7387817399"));
                if(!marshmallowPermissionsCheck.checkPermissionForCall())
                {
                    //request for permission
                    marshmallowPermissionsCheck.requestPermissionForCall();
                }
                else
                {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    } else {
                        startActivity(callIntent);
                    }
                }
                break;
        }
    }
}
