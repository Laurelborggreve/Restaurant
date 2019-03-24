package com.example.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;
    private ArrayList<String> list_categories;
    private JSONArray array_categories;


    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context cont) {
        context = cont;
    }

    public void getCategories(Callback activity) {
        this.activity = activity;
        String url = "https://resto.mprog.nl/categories";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            list_categories = new ArrayList<>();
            array_categories = response.getJSONArray("categories");
            for (int i = 0; i < array_categories.length(); i++) {
                list_categories.add(array_categories.getString(i));
            }
            activity.gotCategories(list_categories);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



