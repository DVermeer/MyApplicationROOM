package com.example.myapplicationroom;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class CreateBeer extends AppCompatActivity {

    private static final String TAG = "CreateUser";

    EditText beer_name;
    EditText brewery;
    EditText alc_percentage;
    EditText new_amount;

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        beer_name = findViewById(R.id.beer_name);
        brewery = findViewById(R.id.brewery);
        alc_percentage = findViewById(R.id.alc_percentage);
        new_amount = findViewById(R.id.new_amount);

        button = findViewById(R.id.button);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Onclick: beerName: " + beer_name.getText().toString());
                Beer beer = new Beer(beer_name.getText().toString(), brewery.getText().toString(), alc_percentage.getText().toString(), new_amount.getText().toString());
                db.beerDao().insertALL(beer);

                startActivity(new Intent(CreateBeer.this, MainActivity.class));
            }
        });

    }
}
