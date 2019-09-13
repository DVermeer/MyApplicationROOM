package com.example.myapplicationroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

class UserAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<Beer> beers;

    public UserAdapter(List<Beer> beers) {
        this.beers = beers;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_row, parent , false);
        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView beerName;
        public TextView brewery;
        public TextView alc_percentage;
        public TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            beerName = itemView.findViewById(R.id.beer_name);
            brewery = itemView.findViewById(R.id.brewery);
            alc_percentage = itemView.findViewById(R.id.alc_percentage);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
