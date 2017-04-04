package com.rasp.lawyersapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.newrelic.com.google.gson.Gson;
import com.rasp.lawyersapp.Adapters.RecyclerViewCategoryListAdapter;
import com.rasp.lawyersapp.Constants.ServerRequestConstants;
import com.rasp.lawyersapp.Constants.Tags;
import com.rasp.lawyersapp.Interfaces.OnReciveServerResponse;
import com.rasp.lawyersapp.Interfaces.RecyclerItemClickListener;
import com.rasp.lawyersapp.MainActivity;
import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.VolleyRequest.VolleyStringRequest;
import com.rasp.lawyersapp.pojos.CategoryList.CategoryList;
import com.rasp.lawyersapp.utility.FragmentTransition;
import com.rasp.lawyersapp.view.EditTextDrawableCustomize;

import org.json.JSONArray;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements OnReciveServerResponse, View.OnClickListener {

    Button btn_clear_text;
    EditTextDrawableCustomize edt_search;
    private RecyclerView mRecyclerViewcategoryList;
    private RecyclerView.Adapter mAdapterCategoryList;
    private LinearLayoutManager mLayoutManagerCategoryList;
    private ArrayList<CategoryList> arrayCategoryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int a = 10;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        return view;
    }

    private void initView(@NonNull View view) {
        btn_clear_text = (Button) view.findViewById(R.id.btn_clear_text);
        btn_clear_text.setOnClickListener(this);
        edt_search = (EditTextDrawableCustomize) view.findViewById(R.id.edt_search);

        mRecyclerViewcategoryList = (RecyclerView) view.findViewById(R.id.mRecyclerViewcategoryList);
        mRecyclerViewcategoryList.setHasFixedSize(true);
        arrayCategoryList = new ArrayList<CategoryList>();
        arrayCategoryList.clear();
        mLayoutManagerCategoryList = new LinearLayoutManager(getActivity());
        mRecyclerViewcategoryList.setLayoutManager(mLayoutManagerCategoryList);
        mAdapterCategoryList = new RecyclerViewCategoryListAdapter(getActivity(), arrayCategoryList);
        mRecyclerViewcategoryList.setAdapter(mAdapterCategoryList);

        mRecyclerViewcategoryList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerViewcategoryList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Tags.category_id, arrayCategoryList.get(position).getCategoryId());
                bundle.putString(Tags.label, arrayCategoryList.get(position).getLabel());
                FragmentTransition.showFragmentAnimation(getActivity(), new FindFragment(), R.id.main_content, bundle, null);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));

        callLawyerCategoriesApi("app-meta-v1-9108010345", "[B@1f14bf37");

    }

    private void callLawyerCategoriesApi(String appID, String authToken) {
        String param = "?applicationId=app-meta-v1-9108010345&authToken=[B@1f14bf37";
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest();
        volleyStringRequest.VolleyRequest(getContext(), this, ServerRequestConstants.category_api, param, null, Tags.GET);
    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.btn_clear_text:
                edt_search.setText("");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.frag_index = 0;
    }

    @Override
    public void setOnReciveResult(String apiName, String result) {

        if (apiName.contains(ServerRequestConstants.category_api)) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    CategoryList categoryList = new CategoryList();
                    categoryList.setCategoryId(jsonArray.getJSONObject(i).getInt(Tags.category_id));
                    categoryList.setLabel(jsonArray.getJSONObject(i).getString(Tags.label));
                    categoryList.setDescription(jsonArray.getJSONObject(i).getString(Tags.description));
                    categoryList.setIconUrl(jsonArray.getJSONObject(i).getString(Tags.icon_url));
                    arrayCategoryList.add(categoryList);
                }
                mAdapterCategoryList.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", "Exception : " + e);
            }
        }
    }


}
