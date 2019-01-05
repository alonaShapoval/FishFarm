package com.bignerdranch.android.fishfarm;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by User on 005 05.01.19.
 */

public class WaterCondition {
    private Context mContext;
    private int method;

    public WaterCondition(Context mContext, int method) {
        this.mContext = mContext;
        this.method = method;
    }
    public void getOxygen(@NonNull final FishFarmServiceCallback callback){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url =Constants.URL+"average_water_condition";

        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onResult(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResult("Error");
            }
        });


        queue.add(stringRequest);

    }

    public interface FishFarmServiceCallback {
        void onResult(String result);
    }

}
