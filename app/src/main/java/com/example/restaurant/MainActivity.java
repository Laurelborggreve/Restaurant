package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    private CategoriesAdapter adapter;

    // Method to start the request for categories
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CategoriesRequest categoriesrequest = new CategoriesRequest(this);
        categoriesrequest.getCategories(this);
    }

    // Method to set adapter
    @Override
    public void gotCategories(ArrayList<String> categories) {
        adapter = new CategoriesAdapter(this, R.layout.adapter_category, categories);
        ListView menuItems = findViewById(R.id.menu_items);
        menuItems.setAdapter(adapter);
        menuItems.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Method to show dishes from a category once it is clicked
    private class ItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            String ClickedCategory = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", ClickedCategory);
            startActivity(intent);
        }
    }
}
