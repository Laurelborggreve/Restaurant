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
import com.squareup.picasso.Picasso;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList<MenuItem> listMenuItems;

    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        listMenuItems = objects;
    }

    // Method to load the layout for each grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_menu, parent, false);
        }

        // Get access to layout's views: image, name, and price and of the dish
        MenuItem MenuItem = listMenuItems.get(position);

        ImageView viewMenuImage = convertView.findViewById(R.id.view_menu_image);
        Picasso.get().load(MenuItem.getImageUrl()).into(viewMenuImage);

        TextView viewMenuName = convertView.findViewById(R.id.view_menu_name);
        viewMenuName.setText(MenuItem.getName());

        TextView viewMenuPrice = convertView.findViewById(R.id.view_menu_price);
        viewMenuPrice.setText("€" + MenuItem.getPrice());

        return convertView;
    }
}
