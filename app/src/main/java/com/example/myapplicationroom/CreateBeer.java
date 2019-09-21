package com.example.myapplicationroom;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class CreateBeer extends AppCompatActivity {

    private static final String TAG = "CreateUser";

    EditText beer_name;
    EditText brewery;
    EditText alc_percentage;
    EditText new_amount;
    EditText EAN_code;

    Button btn_savebeer, btn_checkEAN, btn_scanEAN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        beer_name = findViewById(R.id.beer_name);
        brewery = findViewById(R.id.brewery);
        alc_percentage = findViewById(R.id.alc_percentage);
        new_amount = findViewById(R.id.new_amount);
        EAN_code = findViewById(R.id.EAN_code);
        btn_checkEAN = findViewById(R.id.btn_checkEAN);
        btn_scanEAN = findViewById(R.id.btn_scanEAN);
        btn_savebeer = findViewById(R.id.btn_savebeer);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        btn_savebeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Onclick: beerName: " + beer_name.getText().toString());
                Beer beer = new Beer(beer_name.getText().toString(), brewery.getText().toString(), alc_percentage.getText().toString(), new_amount.getText().toString(), EAN_code.getText().toString());
                db.beerDao().insertALL(beer);

                startActivity(new Intent(CreateBeer.this, MainActivity.class));
            }
        });
        btn_checkEAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EAN_beer = EAN_code.getText().toString();
                Beer beer = db.beerDao().getBeerByEAN(EAN_beer);

                Toast.makeText(getApplicationContext(), "Found " + beer.getBeerName() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_scanEAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
