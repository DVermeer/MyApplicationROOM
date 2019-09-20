package com.example.myapplicationroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Beer {

    public Beer(String beerName, String brewery, String alc_percentage, String amount          ) {
        this.beerName = beerName;
        this.brewery = brewery;
        this.alc_percentage = alc_percentage;
        this.amount = amount;
//        this.EAN_code = EAN_code; // Also Enter String EAN above
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "beer_name")
    private String beerName;

    @ColumnInfo(name = "brewery")
    private String brewery;

    @ColumnInfo(name = "alc_percentage")
    private String alc_percentage;

    @ColumnInfo(name = "amount")
    private String amount;

//    @ColumnInfo(name = "EAN_code")
//    private String EAN_code;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getAlc_percentage() {
        return alc_percentage;
    }

    public void setAlc_percentage(String alc_percentage) {
        this.alc_percentage = alc_percentage;
    }

    public String getAmount() {return amount;  }

    public void setAmount(String amount) { this.amount = amount;  }

//    public String getEAN_code() { return EAN_code;    }
//
//    public void setEAN_code(String EAN_code) { this.EAN_code = EAN_code;    }
}
