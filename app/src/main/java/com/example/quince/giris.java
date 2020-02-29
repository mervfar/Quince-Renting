package com.example.quince;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class giris extends AppCompatActivity {
    FragmentManager fragmentManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        fragmentManager=getSupportFragmentManager();
    }

    public void onClick(View viev){
        switch (viev.getId()){

            case R.id.fragment_a:
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentA fragmentA = new fragmentA();
                fragmentTransaction1.replace(R.id.contain,fragmentA);
                fragmentTransaction1.commit();
                break;

            case R.id.fragment_b:
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentB fragmentB = new fragmentB();
                fragmentTransaction2.replace(R.id.contain,fragmentB);
                fragmentTransaction2.commit();
                break;
        }
    }
}
