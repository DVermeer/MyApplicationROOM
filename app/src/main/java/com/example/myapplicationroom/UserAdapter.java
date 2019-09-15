package com.example.myapplicationroom;

import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

class UserAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String TAG = "Beeradapter";
    private OnBeerListener mOnBeerListener;
    private List<Beer> beers;


    public UserAdapter(List<Beer> beers, OnBeerListener onBeerListener) {
        this.beers = beers;
        this.mOnBeerListener = onBeerListener;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_row, parent , false);
        return new ViewHolder(view, mOnBeerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.beerName.setText(beers.get(position).getBeerName());
        holder.brewery.setText("Brewery: " + beers.get(position).getBrewery());
        holder.alc_percentage.setText("Alcohol percentage: " + beers.get(position).getAlc_percentage());
        holder.amount.setText("Stock: " + beers.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView beerName;
        public TextView brewery;
        public TextView alc_percentage;
        public TextView amount;
            OnBeerListener onBeerListener;

        public ViewHolder(@NonNull View itemView, OnBeerListener onBeerListener) {
            super(itemView);
            this.beerName = itemView.findViewById(R.id.beer_name);
            this.brewery = itemView.findViewById(R.id.brewery);
            this.alc_percentage = itemView.findViewById(R.id.alc_percentage);
            this.amount = itemView.findViewById(R.id.amount);
            this.onBeerListener = onBeerListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBeerListener.onBeerClick(getAdapterPosition());
        }
    }
    public interface OnBeerListener{
        void onBeerClick ( int position);


    }
}
