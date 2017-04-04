package com.rasp.lawyersapp.utility;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the application class containing all the common methods.
 */
public class AppDelegate extends Application {
    public static final String newrelicAppKey = "AA677c606ee9af2b6c778ee8ee41acb08556b09bde";


    public static boolean CheckEmail(@NonNull String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




    public static boolean haveNetworkConnection(Context mContext) {
        return haveNetworkConnection(mContext, true);
    }

    public static boolean haveNetworkConnection(@Nullable Context mContext, boolean showAlert) {
        boolean isConnected = false;
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        if (mContext == null) {
            return false;
        } else {
            ConnectivityManager cm = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
            isConnected = haveConnectedWifi || haveConnectedMobile;
            if (isConnected) {
                return isConnected;
            } else {
                if (showAlert) {
                    AppDelegate.showToast(mContext, "Please make sure that your device is " +
                            "connected to active internet connection.");
                }
            }
            return isConnected;
        }
    }

    public static void showToast(@Nullable Context mContext, String Message) {
        try {
            if (mContext != null)
                Toast.makeText(mContext, Message, Toast.LENGTH_SHORT).show();
            else
                Log.e("tag", "context is null at showing toast.");
        } catch (Exception e) {
            Log.e("tag", "context is null at showing toast.", e);
        }
    }



    /**
     * Method to Hide Soft Input Keyboard
     *
     * @param mContext
     * @param view
     */

    public static void HideKeyboardMain(@NonNull Activity mContext, @NonNull View view) {

        try {


            InputMethodManager imm = (InputMethodManager) mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            // R.id.search_img
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        } catch (Exception e) {
            //Utility.debug(1, TAG, "Exception in executing HideKeyboardMain()");
            e.printStackTrace();
        }
    }


    public static void hideKeyBoard(@Nullable final Activity mActivity, long timeAfter) {
        if (mActivity != null)
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    AppDelegate.hideKeyBoard(mActivity);
                }
            }, timeAfter);
    }

    public static void hideKeyBoard(@Nullable Activity mActivity) {
        if (mActivity == null)
            return;
        else {
            InputMethodManager inputManager = (InputMethodManager) mActivity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            // check if no view has focus:
            View v = mActivity.getCurrentFocus();
            if (v == null)
                return;

            Log.e("Msg ","hideKeyBoard viewNot null");
            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Nullable
    public static String getHashKey(@NonNull Context mContext) {
        String str_HashKey = null;
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                str_HashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("HashKey = ","" + str_HashKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Exceptio : ", ""+e);
        } catch (NoSuchAlgorithmException e) {
            Log.e("Exceptio : ", ""+e);
        }
        return str_HashKey;
    }



}
