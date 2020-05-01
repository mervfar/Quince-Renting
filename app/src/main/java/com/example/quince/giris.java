package com.example.quince;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class giris extends AppCompatActivity {

    Button giris;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);


        giris = (Button) findViewById(R.id.giris);
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), anaSayfa.class);
                startActivity(intent);
                user = new String();
                //user.setType(type);
                //sendRequest(user);

            }
        });


        Button kayit = (Button) findViewById(R.id.kayit);
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), kayit.class);
                startActivity(intent);
            }
        });
        Button uye_olma = (Button) findViewById(R.id.uye_olma);
        uye_olma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), anaSayfa.class);
                startActivity(intent);
            }
        });

    }
}
