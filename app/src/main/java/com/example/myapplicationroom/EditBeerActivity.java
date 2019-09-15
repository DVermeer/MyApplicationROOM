package com.example.myapplicationroom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static java.lang.Integer.*;


public class EditBeerActivity extends AppCompatActivity {

    //TextView beerstock;

    private static final String TAG = "EditBeerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbeer);

        Log.d(TAG, "onCreate: Called");
        if (getIntent().hasExtra("beer_name")) {
            String beer_name = getIntent().getStringExtra("beer_name");
            setBeernaam(beer_name);
        }

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
                }
                else{
                    Toast.makeText(getApplicationContext(), "Out of stock :( Please buy some new one", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void setBeernaam(String beer_name){
        TextView name = findViewById(R.id.beer_name);
                name.setText(beer_name);
        //Toast.makeText(getApplicationContext(), "Cheers: " + beer_name, Toast.LENGTH_SHORT).show();
    }

}
