package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList<MenuItem> adapter_menuitems;

    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.adapter_menuitems = objects;
    }

    // Method to load the layout for each grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_menu, parent, false);
        }

        // Get access to layout's views
        MenuItem MenuItem = adapter_menuitems.get(position);
        TextView view_menu_name = convertView.findViewById(R.id.view_menu_name);
        view_menu_name.setText(MenuItem.getName());

        TextView view_menu_price = convertView.findViewById(R.id.view_menu_price);
        view_menu_price.setText("\u20ac" + Integer.toString(MenuItem.getPrice()));

        ImageView view_menu_image = convertView.findViewById(R.id.view_menu_image);


        return convertView;
    }
}
