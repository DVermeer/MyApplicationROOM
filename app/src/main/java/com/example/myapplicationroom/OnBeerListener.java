package com.example.myapplicationroom;

import android.view.View;

public interface OnBeerListener{
    void onBeerClick  (View view, int position);
    void onDrinkClick(View view, int position);
}
