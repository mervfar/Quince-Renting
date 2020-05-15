package com.example.quince.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quince.R;

public class araba2 extends AppCompatActivity {

    ImageView resim;
    TextView brand;
    TextView price;
    TextView transmission;
    TextView carbody;
    TextView series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba2);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resim = (ImageView) findViewById(R.id.image);
        brand = (TextView) findViewById(R.id.marka);
        price = (TextView) findViewById(R.id.fiyat);
        transmission = (TextView) findViewById(R.id.gear);
        carbody = (TextView)findViewById(R.id.carBody);
        series=(TextView)findViewById(R.id.series);

        String image = getIntent().getExtras().getString("image");
        String marka = getIntent().getExtras().getString("marka");
        String fiyat = getIntent().getExtras().getString("fiyat");
        String gear = getIntent().getExtras().getString("gear");
        String body = getIntent().getExtras().getString("carbody");
        String seri = getIntent().getExtras().getString("series");

        brand.setText(marka);
        price.setText(fiyat);
        transmission.setText(gear);
        carbody.setText(body);
        series.setText(seri);


        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.ic_refresh_black_24dp)
                .into(resim);

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
