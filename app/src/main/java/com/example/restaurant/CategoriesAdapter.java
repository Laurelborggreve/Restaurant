package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends ArrayAdapter {
    private ArrayList<String> adapterCategories;

    public CategoriesAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        adapterCategories = objects;
    }

    // Method to load the layout for each grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_category, parent, false);
        }

        // Get access to layout's views
        TextView viewCategory = convertView.findViewById(R.id.view_category);
        viewCategory.setText(adapterCategories.get(position));

        return convertView;
    }
}
