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
  //  private CategoriesAdapter adapter;
  private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest categoriesrequest = new CategoriesRequest(this);
        categoriesrequest.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        adapter = new CategoriesAdapter(this, R.layout.activity_main, categories);
        ListView listview = findViewById(R.id.menuitems);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         //   String ClickedCategory = (String) parent.getItemAtPosition(position); geswitcht van level
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            String ClickedCategory = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", ClickedCategory);
            startActivity(intent);
        }
    }
}
