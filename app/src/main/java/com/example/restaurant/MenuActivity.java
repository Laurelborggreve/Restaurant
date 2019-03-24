package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve category string
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Ensure string is part of the request for menu items
        MenuRequest menurequest = new MenuRequest(this);
        menurequest.getMenuItems(this, category);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> MenuItems) {
        MenuAdapter adapter_menu = new MenuAdapter(this, R.layout.adapter_category, MenuItems);
        ListView listview = findViewById(R.id.menuitems);
        listview.setAdapter(adapter_menu);
        listview.setOnItemClickListener(new MenuItemClickListener());
    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    private class MenuItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem MenuItem = (MenuItem) parent.getItemAtPosition(position);
            intent.putExtra("item", MenuItem);
            startActivity(intent);
        }

    }
}
