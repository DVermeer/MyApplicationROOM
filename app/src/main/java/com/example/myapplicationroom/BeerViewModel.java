package com.example.myapplicationroom;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

public abstract class BeerViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private BeerDao beerDao;
    private AppDatabase beerDB;

    public BeerViewModel(Application application){
        super(application);

       // beerDB = AppDatabase.getDatabase(application);
        beerDao = beerDB.beerDao();
    }

    public void insert(Beer beer){
        new InsertAsyncTask(beerDao).execute(beer);
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Log.i(TAG,"Viewmodel destroyed");
    }
    private class InsertAsyncTask extends AsyncTask<Beer, Void, Void > {

        BeerDao mBeerDao;

        public InsertAsyncTask(BeerDao mBeerDao){
            this.mBeerDao = mBeerDao;
        }

        @Override
        protected Void doInBackground(Beer... beers){
            mBeerDao.insertALL(beers[0]);
            return null;
        }
    }
}
