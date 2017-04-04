package com.rasp.lawyersapp.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rasp.lawyersapp.Constants.MarshmallowPermissionsCheck;
import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.pojos.CategoryList.LawyerList.LawyerList;
import com.rasp.lawyersapp.view.TextViewCustomize;

import java.util.ArrayList;

/**
 * Created by rasp on 15/3/17.
 */

public class RecyclerViewLawyersListAdapter extends RecyclerView.Adapter<RecyclerViewLawyersListAdapter.RecyclerViewLawyersListViewHolder> implements View.OnClickListener {
    public Context context;
    Activity activity;
    private ArrayList<LawyerList> arrayLawyersList;
    MarshmallowPermissionsCheck marshmallowPermissionsCheck;
    public RecyclerViewLawyersListAdapter(Context context, Activity activity, ArrayList<LawyerList> arrayLawyersList) {
        this.arrayLawyersList = arrayLawyersList;
        this.context = context;
        this.activity = activity;
        marshmallowPermissionsCheck = new MarshmallowPermissionsCheck(activity);
    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId())
        {
            case R.id.txt_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7387817399"));
                if(!marshmallowPermissionsCheck.checkPermissionForCall())
                {
                    //request for permission
                    marshmallowPermissionsCheck.requestPermissionForCall();
                }
                else
                {
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    } else {
                        context.startActivity(callIntent);
                    }
                }
                break;
        }
    }

    public class RecyclerViewLawyersListViewHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextViewCustomize txt_lawyer_name, txt_lawyer_degree, txt_experience, txt_review_percent, txt_location, txt_review_count, txt_call;
        public ImageView cim_image;

        public RecyclerViewLawyersListViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_lawyer_name = (TextViewCustomize) itemView.findViewById(R.id.txt_lawyer_name);
            txt_lawyer_degree = (TextViewCustomize) itemView.findViewById(R.id.txt_lawyer_degree);
            txt_experience = (TextViewCustomize) itemView.findViewById(R.id.txt_experience);
            txt_review_percent = (TextViewCustomize) itemView.findViewById(R.id.txt_review_percent);
            txt_location = (TextViewCustomize) itemView.findViewById(R.id.txt_location);
            txt_review_count = (TextViewCustomize) itemView.findViewById(R.id.txt_review_count);
            txt_call = (TextViewCustomize) itemView.findViewById(R.id.txt_call);

            cim_image = (ImageView) itemView.findViewById(R.id.cim_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public RecyclerViewLawyersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_lawyer_list_item, parent, false);

        RecyclerViewLawyersListViewHolder recyclerViewLawyersListViewHolder = new RecyclerViewLawyersListViewHolder(view);
        return recyclerViewLawyersListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLawyersListViewHolder holder, int position) {
        holder.cim_image.setTag(position);
        holder.txt_lawyer_name.setTag(position);
        holder.txt_lawyer_degree.setTag(position);
        holder.txt_experience.setTag(position);

        holder.txt_review_percent.setTag(position);
        holder.txt_location.setTag(position);
        holder.txt_review_count.setTag(position);
        holder.txt_call.setTag(position);

        holder.txt_lawyer_name.setText(arrayLawyersList.get(position).getFirstName()+" " +arrayLawyersList.get(position).getLastName());
        holder.txt_lawyer_degree.setText(arrayLawyersList.get(position).getQualifications());
        holder.txt_experience.setText(arrayLawyersList.get(position).getExperience()+" years Experience");
        holder.txt_review_percent.setText(arrayLawyersList.get(position).getRating().toString());

        holder.txt_call.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return arrayLawyersList.size();
    }


}

