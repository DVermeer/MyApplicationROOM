package com.example.myapplicationroom;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnBeerListener {

    private static final String TAG = "MainActivity";
    List<Beer> beers;
    RecyclerView recylcerview;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recylcerview = findViewById(R.id.recycler_view);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        beers = db.beerDao().getOnStockBeers();

        recylcerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(beers, this);
        recylcerview.setAdapter(adapter);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateBeer.class));
            }
        });
    }

    @Override
    public void onBeerClick(int position) {
        Toast.makeText(getApplicationContext(), "Cheers", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent ( MainActivity.this, EditBeerActivity.class);

        // ToDo String for beer name
//        String beer_Name = beerName;
        String i_beer = position + "";
        intent.putExtra("beer_name", i_beer);
        startActivity(intent);

    }

    @Override
    public void onDrinkClick(View view, int adapterPosition, String new_stock) {
       Beer beer_name = beers.get(adapterPosition);
        Toast.makeText(getApplicationContext(), "Cheers, Enjoy your " + beer_name.getBeerName() + "!", Toast.LENGTH_SHORT).show();
        beer_name.setAmount(new_stock);

        // ToDo classify db elsewhere globally so this become redundant
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        db.beerDao().updateBeers(beer_name);
        adapter.notifyDataSetChanged();
    }
}


