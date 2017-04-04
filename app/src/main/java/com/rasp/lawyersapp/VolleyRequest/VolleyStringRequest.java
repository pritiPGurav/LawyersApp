package com.rasp.lawyersapp.VolleyRequest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rasp.lawyersapp.Constants.ServerRequestConstants;
import com.rasp.lawyersapp.Interfaces.OnReciveServerResponse;

import org.json.JSONArray;


/**
 * Created by rasp on 2/4/17.
 */

public class VolleyStringRequest {

    private String result;
    private OnReciveServerResponse mOnReciveServerResponse;
    private RequestQueue queue;

    public void VolleyRequest(Context context, OnReciveServerResponse onReciveServerResponse, final String str_PostApiName, String strParamList, Fragment fragment, int requestType) {
        queue = Volley.newRequestQueue(context);
        this.mOnReciveServerResponse = onReciveServerResponse;

        StringRequest postRequest = new StringRequest(requestType, ServerRequestConstants.BaseUrl + str_PostApiName + strParamList, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                result = response;
                Log.e("Response", "Response : " + result);
                try {
                    if (mOnReciveServerResponse != null)
                        mOnReciveServerResponse.setOnReciveResult(str_PostApiName, result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Second trace", "second trace" + e);

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error at", "Error at" + "Invalid request has sent" + error);
                    }
                }
        ) {
        };
        // Add the request to the RequestQueue.
        queue.add(postRequest);
    }
}
