package com.example.myapplicationroom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import static java.lang.Integer.*;


public class EditBeerActivity extends AppCompatActivity {

    TextView beerstock;
    //AppDatabase db;

    private static final String TAG = "EditBeerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbeer);

        // ToDo Call database here, but should me implemented in Appdatabase //
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        List<Beer> beers = db.beerDao().getAllBeers();

        Log.d(TAG, "onCreate: Called");
        if (getIntent().hasExtra("beer_name")) {
            String beer_index = getIntent().getStringExtra("beer_name");
            int beer_ind = parseInt(beer_index);
            Beer beer_selected = beers.get(beer_ind);
            String beer_name = beer_selected.getBeerName();
            String beer_stock = beer_selected.getAmount();
            setBeernaam(beer_name, beer_stock);


        Button btnDrink = findViewById(R.id.btnDrink);
        final TextView beerstock = findViewById(R.id.beerstock);

        btnDrink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Cheers", Toast.LENGTH_SHORT).show();
                // get Text from id/beerstock -> int, operate -1, set to string, set to text
                String stock = beerstock.getText().toString();
                int stock_num = parseInt(stock);
                if (stock_num > 0 ){
                    stock_num = stock_num -1 ;
                    String stock_newtext = stock_num + "";
                    beerstock.setText(stock_newtext);
                    Toast.makeText(getApplicationContext(), "Cheers, enjoy your beer", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Out of stock :( Please buy some new one", Toast.LENGTH_SHORT).show();

                }
            }
        });
        String stock_newtext = beerstock.getText().toString();
        beer_selected.setAmount(stock_newtext);

        }
    }

    public void setBeernaam(String beer_name, String beer_stock){
        TextView name = findViewById(R.id.beer_name);
        TextView stock = findViewById(R.id.beerstock);
                name.setText(beer_name);
                stock.setText(beer_stock);

    }

}
