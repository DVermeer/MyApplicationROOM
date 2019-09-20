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
    AppDatabase db;
    RecyclerView recylcerview;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    List<Beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recylcerview = findViewById(R.id.recycler_view);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        beers = db.beerDao().getAllBeers();

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
       String beer_name = beers.get(adapterPosition).getBeerName();
        Toast.makeText(getApplicationContext(), "Cheers, Enjoy your " + beer_name + "!", Toast.LENGTH_SHORT).show();
        beers.get(adapterPosition).setAmount(new_stock);
        //db.beerDao().updateBeers();
        adapter.notifyDataSetChanged();
    }
}


