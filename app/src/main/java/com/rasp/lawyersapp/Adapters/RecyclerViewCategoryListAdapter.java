package com.rasp.lawyersapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.pojos.CategoryList.CategoryList;

import java.util.ArrayList;

/**
 * Created by rasp on 15/3/17.
 */

public class RecyclerViewCategoryListAdapter extends RecyclerView.Adapter<RecyclerViewCategoryListAdapter.RecyclerViewCategoryViewHolder> {
    public Context context;
    private ArrayList<CategoryList> arrayLawyersList;

    public RecyclerViewCategoryListAdapter(Context context, ArrayList<CategoryList> arrayLawyersList) {
        this.arrayLawyersList = arrayLawyersList;
        this.context = context;
    }

    public class RecyclerViewCategoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView txt_category_name;
        public ImageView cim_image;

        public RecyclerViewCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_category_name = (TextView) itemView.findViewById(R.id.txt_category_name);
            cim_image = (ImageView) itemView.findViewById(R.id.cim_image);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public RecyclerViewCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);

        RecyclerViewCategoryViewHolder recyclerViewLawyersListViewHolder = new RecyclerViewCategoryViewHolder(view);
        return recyclerViewLawyersListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCategoryViewHolder holder, int position) {
        holder.cim_image.setTag(position);
        holder.txt_category_name.setTag(position);

        holder.txt_category_name.setText(arrayLawyersList.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        return arrayLawyersList.size();
    }


}

