package com.rasp.lawyersapp.Fragments;

import android.content.Intent;
import android.nfc.Tag;
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
import android.widget.Switch;
import android.widget.Toast;

import com.rasp.lawyersapp.Activities.LawyersDetails;
import com.rasp.lawyersapp.Adapters.RecyclerViewLawyersListAdapter;
import com.rasp.lawyersapp.Constants.ServerRequestConstants;
import com.rasp.lawyersapp.Constants.Tags;
import com.rasp.lawyersapp.Interfaces.OnReciveServerResponse;
import com.rasp.lawyersapp.Interfaces.RecyclerItemClickListener;
import com.rasp.lawyersapp.MainActivity;
import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.VolleyRequest.VolleyStringRequest;
import com.rasp.lawyersapp.pojos.CategoryList.CategoryList;
import com.rasp.lawyersapp.pojos.CategoryList.LawyerList.CategoryId;
import com.rasp.lawyersapp.pojos.CategoryList.LawyerList.LawyerList;
import com.rasp.lawyersapp.utility.FragmentTransition;
import com.rasp.lawyersapp.view.EditTextDrawableCustomize;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FindFragment extends Fragment implements OnReciveServerResponse, View.OnClickListener{
    Button btn_clear_text;
    EditTextDrawableCustomize edt_search;
    private RecyclerView mRecyclerViewLawyersList;
    private RecyclerView.Adapter mAdapterLawyersList;
    private LinearLayoutManager mLayoutManagerLawyersList;
    private ArrayList<LawyerList> arrayLawyersList;
    Bundle bundle;
    int categoryId;
    String categoryLable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle = getArguments();
        if (bundle != null) {
            categoryId = bundle.getInt(Tags.category_id);
            categoryLable = bundle.getString(Tags.label);
        }
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initView(view);
        return view;
    }

    private void initView(@NonNull View view) {
        btn_clear_text = (Button)view.findViewById(R.id.btn_clear_text);
        btn_clear_text.setOnClickListener(this);
        edt_search = (EditTextDrawableCustomize)view.findViewById(R.id.edt_search);

        mRecyclerViewLawyersList = (RecyclerView)view.findViewById(R.id.mRecyclerViewLawyersList);
        mRecyclerViewLawyersList.setHasFixedSize(true);
        arrayLawyersList = new ArrayList<LawyerList>();
        /*arrayLawyersList.add("Jolly");
        arrayLawyersList.add("Sunder");
        arrayLawyersList.add("Kurpreet");
        arrayLawyersList.add("Raawan");
        arrayLawyersList.add("Daizy");*/

        mLayoutManagerLawyersList = new LinearLayoutManager(getActivity());
        mRecyclerViewLawyersList.setLayoutManager(mLayoutManagerLawyersList);
        mAdapterLawyersList = new RecyclerViewLawyersListAdapter(getActivity(), getActivity(), arrayLawyersList);
        mRecyclerViewLawyersList.setAdapter(mAdapterLawyersList);

        mRecyclerViewLawyersList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerViewLawyersList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), LawyersDetails.class));
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));

        callLawyerListApi("app-meta-v1-9108010345", "[B@1f14bf37");

    }

    private void callLawyerListApi(String appID, String authToken) {
        String param = "?applicationId=app-meta-v1-9108010345&authToken=[B@1f14bf37&categoryId="+categoryId+"&label="+categoryLable;
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest();
        volleyStringRequest.VolleyRequest(getContext(), this, ServerRequestConstants.lawyer_list, param, null, Tags.GET);
    }


    @Override
    public void onClick(@NonNull View v) {
        switch(v.getId())
        {
            case R.id.btn_clear_text:
                edt_search.setText("");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.frag_index = 1;
    }

    @Override
    public void setOnReciveResult(String apiName, String result) {
        if (apiName.contains(ServerRequestConstants.lawyer_list)) {
            try {
                arrayLawyersList.clear();
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    LawyerList lawyerList = new LawyerList();
                    lawyerList.setDateOfBirth(jsonArray.getJSONObject(i).getInt(Tags.dateOfBirth));
                    //lawyerList.setAddress(jsonArray.getJSONObject(i).getString(Tags.address));
                    lawyerList.setLawyerId(jsonArray.getJSONObject(i).getInt(Tags.lawyer_id));
                    lawyerList.setFirstName(jsonArray.getJSONObject(i).getString(Tags.first_name));
                    lawyerList.setLastName(jsonArray.getJSONObject(i).getString(Tags.last_name));
                    lawyerList.setEmail(jsonArray.getJSONObject(i).getString(Tags.email));
                    lawyerList.setPortraitUrl(jsonArray.getJSONObject(i).getString(Tags.portrait_url));
                    lawyerList.setGender(jsonArray.getJSONObject(i).getString(Tags.gender));
                    lawyerList.setMobileNumber(jsonArray.getJSONObject(i).getInt(Tags.mobile_number));
                    lawyerList.setLandLineNumber(jsonArray.getJSONObject(i).getInt(Tags.land_line_number));
                    lawyerList.setWebsite(jsonArray.getJSONObject(i).getString(Tags.website));
                    lawyerList.setQualifications(jsonArray.getJSONObject(i).getString(Tags.qualifications));
                    lawyerList.setDesignation(jsonArray.getJSONObject(i).getString(Tags.designation));
                    lawyerList.setExperience(jsonArray.getJSONObject(i).getInt(Tags.experience));
                    lawyerList.setAboutLawyer(jsonArray.getJSONObject(i).getString(Tags.about_lawyer));
                    lawyerList.setRating(jsonArray.getJSONObject(i).getDouble(Tags.rating));

                    JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("category_id");
                    if(jsonObject!=null){
                        CategoryList categoryList = new CategoryList();
                        categoryList.setCategoryId(Integer.parseInt(jsonObject.getString(Tags.category_id)));
                        categoryList.setLabel(jsonObject.getString(Tags.label));
                        categoryList.setDescription(jsonObject.getString(Tags.description));
                        categoryList.setIconUrl(jsonObject.getString(Tags.icon_url));
                        lawyerList.setCategoryList(categoryList);
                    }
                    arrayLawyersList.add(lawyerList);
                }
                mAdapterLawyersList.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", "Exception : " + e);
            }
        }

    }
}
