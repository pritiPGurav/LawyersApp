package com.rasp.lawyersapp.utility;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by rasp on 15/3/17.
 */

public class FragmentTransition {

    public static void showFragmentAnimation(@Nullable FragmentActivity mContext,
                                             @NonNull android.support.v4.app.Fragment mFragment, int fragmentId, @Nullable Bundle mBundle, String TAG) {

        if (mBundle != null && mContext != null)
            mFragment.setArguments(mBundle);
        try {
            if (mContext != null && mFragment != null)
                mContext.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(fragmentId, mFragment)
                        .addToBackStack(null).commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e("Exception : ",""+e);
        }
    }


    public static void showFragmentAnimationWithBackFlip(@Nullable Activity mContext, @NonNull Fragment mFragment, int fragmentId, @Nullable Bundle mBundle, String TAG) {

        if (mBundle != null && mContext != null)
            mFragment.setArguments(mBundle);
        try {
            FragmentTransaction mFragmentTransaction = mContext
                    .getFragmentManager()
                    .beginTransaction().replace(fragmentId,mFragment).addToBackStack(null);
            mFragmentTransaction.replace(fragmentId, mFragment)
                    .addToBackStack(null).commitAllowingStateLoss();

        } catch (Exception e) {
            Log.e("Exception :", " " + e);
        }
    }

}
