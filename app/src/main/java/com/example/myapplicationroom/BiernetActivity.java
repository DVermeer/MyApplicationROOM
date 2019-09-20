package com.example.myapplicationroom;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class BiernetActivity extends AppCompatActivity {

    private static final String TAG = "BiernetActivity";
    private ProgressDialog progressDialog;
    private String title;
    private TextView link_biernet;
    public String url;
    Spanned link;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biernet);

        TextView link_biernet = findViewById(R.id.link_biernet);

        String beer_name = getIntent().getStringExtra("beer_name");
        Toast.makeText(getApplicationContext(), beer_name, Toast.LENGTH_SHORT).show();

        String[] arr = beer_name.split(" ");
        String url = "https://www.biernet.nl/bier/merken/";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                url = url + arr[i];
            } else {
                url = url + arr[i] + "-";
            }
            //link_biernet.setText(url);
            link = Html.fromHtml("Find on Biernet <br />" +
                    "<a href='"+ url + " '>"+ beer_name + "</a>");
            link_biernet.setMovementMethod(LinkMovementMethod.getInstance());
            link_biernet.setText(link);
        }
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        Elements links = doc.select("ul.informatietabel").select("li");
//        Log.d(TAG, "onCreate:" + links);

    }
}