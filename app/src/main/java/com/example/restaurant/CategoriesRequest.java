package com.example.restaurant;

import android.content.Context;
import android.telecom.Call;

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
    private ArrayList<String> listCategories;
    private JSONArray arrayCategories;


    // Method because data is not instantly returned
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context cont) {
        context = cont;
    }

    // Method to retrieve categories from the API
    public void getCategories(Callback activity) {
        this.activity = activity;
        String url = "https://resto.mprog.nl/categories";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // Method that is called when something goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    // Method that is called when everything goes as expected
    @Override
    public void onResponse(JSONObject response) {
        try {
            listCategories = new ArrayList<>();
            arrayCategories = response.getJSONArray("categories");
            for (int i = 0; i < arrayCategories.length(); i++) {
                listCategories.add(arrayCategories.getString(i));
            }
            activity.gotCategories(listCategories);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



