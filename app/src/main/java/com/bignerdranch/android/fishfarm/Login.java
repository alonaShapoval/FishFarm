package com.bignerdranch.android.fishfarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 005 05.01.19.
 */

public class Login {
    private Context mContext;
    private int method;
    private String email;
    private String password;

    public Login(Context mContext, int method, String email, String password) {
        this.mContext = mContext;
        this.method = method;
        this.email = email;
        this.password = password;
    }

    public void checkLogin(@NonNull final FishFarmServiceCallback callback){
        String url=Constants.URL+"login";
        RequestQueue queue = Volley.newRequestQueue(mContext);

       StringRequest postRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                         //  callback.onResult(response);
                        System.out.println("Hello"+response);
                        callback.onResult(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
           @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
@Override
public Map<String, String> getHeaders() throws AuthFailureError {
    Map<String,String> params = new HashMap<String, String>();
    params.put("Content-Type","application/x-www-form-urlencoded");
    return params;
}
            @Override
            protected Map<String, String> getParams()
            {
                System.out.println("EmailPass"+email+password);
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);


    }
    public interface FishFarmServiceCallback {
        void onResult(String result);
    }

}
