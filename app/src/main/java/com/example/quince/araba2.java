package com.example.quince;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class araba2 extends AppCompatActivity {

    ImageView image;
    TextView marka, fiyat, gear , fuel ,year;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba2);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        image = findViewById(R.id.image);
        marka = findViewById(R.id.marka);
        fiyat = findViewById(R.id.fiyat);
        gear  = findViewById(R.id.gear);
        fuel  = findViewById(R.id.fuel);
        year  = findViewById(R.id.year);

        if (position == 0) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String amarka = intent.getStringExtra("marka");
            String afiyat = intent.getStringExtra("fiyat");
            String agear = intent.getStringExtra("gear");
            String afuel = intent.getStringExtra("fuel");
            String ayear = intent.getStringExtra("year");

            image.setImageResource(pic);
            marka.setText(amarka);
            fiyat.setText(afiyat);
            gear.setText(agear);
            fuel.setText(afuel);
            year.setText(ayear);

            actionBar.setTitle(amarka);
        }

        if (position == 1) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String amarka = intent.getStringExtra("marka");
            String afiyat = intent.getStringExtra("fiyat");
            String agear = intent.getStringExtra("gear");
            String afuel = intent.getStringExtra("fuel");
            String ayear = intent.getStringExtra("year");

            image.setImageResource(pic);
            marka.setText(amarka);
            fiyat.setText(afiyat);
            gear.setText(agear);
            fuel.setText(afuel);
            year.setText(ayear);

            actionBar.setTitle(amarka);
        }

        if (position == 2) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String amarka = intent.getStringExtra("marka");
            String afiyat = intent.getStringExtra("fiyat");
            String agear = intent.getStringExtra("gear");
            String afuel = intent.getStringExtra("fuel");
            String ayear = intent.getStringExtra("year");

            image.setImageResource(pic);
            marka.setText(amarka);
            fiyat.setText(afiyat);
            gear.setText(agear);
            fuel.setText(afuel);
            year.setText(ayear);

            actionBar.setTitle(amarka);
        }

        if (position == 3) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String amarka = intent.getStringExtra("marka");
            String afiyat = intent.getStringExtra("fiyat");
            String agear = intent.getStringExtra("gear");
            String afuel = intent.getStringExtra("fuel");
            String ayear = intent.getStringExtra("year");

            image.setImageResource(pic);
            marka.setText(amarka);
            fiyat.setText(afiyat);
            gear.setText(agear);
            fuel.setText(afuel);
            year.setText(ayear);

            actionBar.setTitle(amarka);
        }

        if (position == 4) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String amarka = intent.getStringExtra("marka");
            String afiyat = intent.getStringExtra("fiyat");
            String agear = intent.getStringExtra("gear");
            String afuel = intent.getStringExtra("fuel");
            String ayear = intent.getStringExtra("year");

            image.setImageResource(pic);
            marka.setText(amarka);
            fiyat.setText(afiyat);
            gear.setText(agear);
            fuel.setText(afuel);
            year.setText(ayear);

            actionBar.setTitle(amarka);
        }

    }

}
