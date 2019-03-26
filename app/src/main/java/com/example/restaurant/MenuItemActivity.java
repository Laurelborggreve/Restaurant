package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

// import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem menuitem = (MenuItem) intent.getSerializableExtra("item");
        setTitle(menuitem.getName());

        TextView detail_name  = findViewById(R.id.detail_name);
        detail_name.setText(menuitem.getName());

        TextView detail_description = findViewById(R.id.detail_description);
        detail_description.setText(menuitem.getDescription());

        TextView detail_price = findViewById(R.id.detail_price);
      //  String price = "\u20BF" + Integer.toString(menuitem.getPrice());
        detail_price.setText("€" + menuitem.getPrice());
      //  detail_price.setText(price);

        ImageView detail_image = findViewById(R.id.detail_image);
      //  Picasso.with(getApplicationContext()).load(menuitem.getImageUrl()).into(detail_image);


    }
}
