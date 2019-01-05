package com.bignerdranch.android.fishfarm;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by User on 003 03.01.19.
 */

public class Temperature {

    private Context mContext;
    private int method;

    public Temperature(Context mContext, int method) {

        this.mContext = mContext;
        this.method = method;
    }


    public void getAllTemperature(@NonNull final FishFarmServiceCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url =Constants.URL+"average_temperature";

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
    public void getPools(@NonNull final FishFarmServiceCallback callback){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url =Constants.URL+"pool?param=id";

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
