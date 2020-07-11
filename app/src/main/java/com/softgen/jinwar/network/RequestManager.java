package com.softgen.jinwar.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestManager {
    private static final String baseUrl = "http://10.0.2.2:8080/Intellext";
    private static RequestManager instance = null;

    RequestQueue requestQueue;

    private RequestManager(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized RequestManager getInstance(Context context)
    {
        if (null == instance)
            instance = new RequestManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized RequestManager getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(RequestManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void executeSyncPostRequest(URLPaths urlPath, final JSONObject headerParams, final VolleyResponseListener volleyResponseListener)
    {
        String url = baseUrl + urlPath.getUrlPath();

        Map<String, Object> jsonParams = new HashMap<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        volleyResponseListener.onError(error.toString());
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();

                params.put("key", "abcd");

                Iterator<String> headerKeys = headerParams.keys();

                while (headerKeys.hasNext()){
                    String headerKey = headerKeys.next();
                    try {
                        String headerValue = headerParams.getString(headerKey);
                        params.put(headerKey, headerValue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return params;
            }};

        requestQueue.add(request);
    }
}
