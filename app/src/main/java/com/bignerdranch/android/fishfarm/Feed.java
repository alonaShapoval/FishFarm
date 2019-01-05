package com.bignerdranch.android.fishfarm;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 005 05.01.19.
 */

public class Feed {
    private Context mContext;
    private int method;

    public Feed(Context mContext, int method) {
        this.mContext = mContext;
        this.method = method;
    }

    public void feed() throws Exception {
String url=Constants.URL+"feeding";
        RequestQueue queue = Volley.newRequestQueue(mContext);

        StringRequest postRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        System.out.println("REsponse: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
//                        Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
        };
        queue.add(postRequest);
        }
    }


