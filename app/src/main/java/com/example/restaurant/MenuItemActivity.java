package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

// import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;
import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    // Method to show the details (name, image, description and price) of the clicked dish
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem retrievedMenuItem = (MenuItem) intent.getSerializableExtra("item");
        setTitle(retrievedMenuItem.getName());

        TextView detailName  = findViewById(R.id.detail_name);
        detailName.setText(retrievedMenuItem.getName());

        ImageView detailImage = findViewById(R.id.detail_image);
        Picasso.get().load(retrievedMenuItem.getImageUrl()).into(detailImage);

        TextView detailDescription = findViewById(R.id.detail_description);
        detailDescription.setText(retrievedMenuItem.getDescription());

        TextView detailPrice = findViewById(R.id.detail_price);
        detailPrice.setText("€" + retrievedMenuItem.getPrice());
    }
}
