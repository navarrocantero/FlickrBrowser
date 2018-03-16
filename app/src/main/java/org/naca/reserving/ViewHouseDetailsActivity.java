package org.naca.reserving;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;
import com.squareup.picasso.Picasso;

public class ViewHouseDetailsActivity extends BaseActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.house_details)
                .setSwipeBackView(R.layout.swipeback_default);

        activateToolbarWithBackEnabled();

        Intent intent = getIntent();
        House house = (House) intent.getSerializableExtra(HOUSE_TRANSFER);

        TextView name = findViewById(R.id.name);
        name.setText(house.getName());
        TextView users = findViewById(R.id.users);
        users.setText("Maximo " + house.getUsers() + " huespedes");
//
        TextView direction = findViewById(R.id.direction);
        direction.setText(house.getDirection());

        TextView description = findViewById(R.id.description);

        description.setText(house.getmDescription());
        description.setMovementMethod(new ScrollingMovementMethod());
        TextView price = findViewById(R.id.price);
        price.setText("Precio por persona y noche " + house.getPrice() + "€");

        ImageView photoImage = findViewById(R.id.image);
        try {
            Picasso.with(this).load(house.getImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(photoImage);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_details, menu);

        return true;
    }


}
