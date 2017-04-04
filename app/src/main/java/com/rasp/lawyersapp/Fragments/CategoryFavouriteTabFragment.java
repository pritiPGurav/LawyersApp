package com.rasp.lawyersapp.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rasp.lawyersapp.Adapters.PagerAdapter;
import com.rasp.lawyersapp.MainActivity;
import com.rasp.lawyersapp.R;
import com.rasp.lawyersapp.view.TextViewCustomize;

import java.util.ArrayList;
import java.util.List;


public class CategoryFavouriteTabFragment extends Fragment implements View.OnClickListener{


    ViewPager mViewPager;
    TextViewCustomize txt_categories, txt_favourites;
    List<Fragment> fragments = new ArrayList<>();
    private PagerAdapter pagerAdapter;
    LinearLayout ll_categories, ll_favourites;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category_favourite_tab, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        mViewPager = (ViewPager) v.findViewById(R.id.mViewPager);

        txt_categories = (TextViewCustomize) v.findViewById(R.id.txt_categories);
        txt_favourites = (TextViewCustomize) v.findViewById(R.id.txt_favourites);
        txt_favourites.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        ll_categories = (LinearLayout) v.findViewById(R.id.ll_categories);
        ll_categories.setOnClickListener(this);
        ll_favourites = (LinearLayout) v.findViewById(R.id.ll_favourites);
        ll_favourites.setOnClickListener(this);

        if (fragments.size() == 0) {
            fragments.add(new CategoryFragment());
            fragments.add(new MeFragment());
        }

        pagerAdapter = new PagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mViewPager.setCurrentItem(0);
                    ll_favourites.setBackgroundColor(getResources().getColor(R.color.colorblack));
                    ll_categories.setBackgroundColor(getResources().getColor(R.color.colorwhite));
                    txt_categories.setTextColor(getResources().getColor(R.color.colorblack));
                    txt_favourites.setTextColor(getResources().getColor(R.color.colorwhite));
                    txt_categories.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    txt_favourites.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                } else {
                    mViewPager.setCurrentItem(1);
                    ll_favourites.setBackgroundColor(getResources().getColor(R.color.colorwhite));
                    ll_categories.setBackgroundColor(getResources().getColor(R.color.colorblack));
                    txt_favourites.setTextColor(getResources().getColor(R.color.colorblack));
                    txt_categories.setTextColor(getResources().getColor(R.color.colorwhite));
                    txt_favourites.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    txt_categories.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_categories:
                mViewPager.setCurrentItem(0);
                break;

            case R.id.ll_favourites:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.frag_index = 0;
    }

}
