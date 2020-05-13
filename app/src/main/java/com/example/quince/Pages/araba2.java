package com.example.quince.Pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quince.Domains.Car.CarBrand;
import com.example.quince.R;

public class araba2 extends AppCompatActivity {

    ImageView image;
    TextView marka;
    TextView fiyat;
    TextView gear;
    TextView fuel;
    TextView year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = (ImageView) findViewById(R.id.image);
        marka = (TextView) findViewById(R.id.marka);
        fiyat = (TextView) findViewById(R.id.fiyat);
        gear = (TextView) findViewById(R.id.gear);
        fuel = (TextView) findViewById(R.id.fuel);
        year = (TextView) findViewById(R.id.year);

        String marka = getIntent().getExtras().getString("marka");
        String fiyat = getIntent().getExtras().getString("fiyat");
        String gear = getIntent().getExtras().getString("gear");
        String fuel = getIntent().getExtras().getString("fuel");
        String year = getIntent().getExtras().getString("year");


    }

    private Intent createShareForcastIntent(){
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer @" + username + ", " + link)
                .getIntent();
        return shareIntent;
    }

}
