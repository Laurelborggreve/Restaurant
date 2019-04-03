package com.example.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;
    private ArrayList<MenuItem> listMenuItems;
    private JSONArray arrayMenuItems;


    // Method because data is not instantly returned
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItemsError (String message);
    }

    public MenuRequest (Context cont){
        context =  cont;
    }

    // Method to retrieve categories from the API
    public void getMenuItems(Callback activity, String category) {
        this.activity = activity;
        String url = "https://resto.mprog.nl/menu?category="+category;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // Method that is called when something goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("onResponse", error.toString());
        activity.gotMenuItemsError(error.getMessage());
    }

    // Method that is called when everything goes as expected
    @Override
    public void onResponse(JSONObject response) {
        Log.d("onResponse",response.toString());
        try {
            listMenuItems = new ArrayList<>();
            arrayMenuItems = response.getJSONArray("items");
            for (int i = 0; i < arrayMenuItems.length(); i++) {
                JSONObject item = arrayMenuItems.getJSONObject(i);
                String name = item.getString("name");
                String description = item.getString("description");
                String image = item.getString("image_url");
                int price = item.getInt("price");
                String category = item.getString("category");
                MenuItem menuitem = new MenuItem(name, description, image, price, category);
                listMenuItems.add(menuitem);
            }
            activity.gotMenuItems(listMenuItems);
        }
        catch (JSONException e) {
            Log.d("onResponse", e.toString());
            e.printStackTrace();
        }
    }
}
