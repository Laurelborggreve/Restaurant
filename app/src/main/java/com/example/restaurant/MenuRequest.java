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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
   // private Callback activity_menu;
   private MenuRequest.Callback activity;
    private ArrayList<MenuItem> list_menuitems;
    private JSONArray array_menuitems;
    private String category;

    public interface Callback {
     //   void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItems(ArrayList<MenuItem> MenuItems);
        void gotMenuItemsError (String message);
    }

    public MenuRequest (Context c, String category){

        context =  c;
        this.category = "?category=" + category;
    }

//    public void getMenuItems(Callback activity, String category) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        String url = "https://resto.mprog.nl/menu?category=entrees"+category;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
//        queue.add(jsonObjectRequest);
//
//        this.activity_menu = activity;
//    }

    public void getMenuItems(MenuRequest.Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

    //    String url = "https://resto.mprog.nl/menu" + category;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu" + category, null, this, this);
        queue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            list_menuitems = new ArrayList<>();
            array_menuitems = response.getJSONArray("items");
            for (int i = 0; i < array_menuitems.length(); i++) {
                JSONObject menuitem = array_menuitems.getJSONObject(i);
                String name = menuitem.getString("name");
                String description = menuitem.getString("description");
                String image = menuitem.getString("image");
                int price = menuitem.getInt("price");
                String category = menuitem.getString("category");
                MenuItem MenuItem = new MenuItem(name, description, image, price, category);
                list_menuitems.add(MenuItem);
            }
            activity.gotMenuItems(list_menuitems);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
