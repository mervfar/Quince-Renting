package com.example.quince;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class anaSayfa extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private LinearLayout mMainFrame;

    private home home; // home fragment tanımı
    private hesabim hesabim; //hesabım fragment tanımı
    private ayarlar ayarlar; //ayarlar fragment tanımı



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        mMainFrame=(LinearLayout)findViewById(R.id.main_frame);
        mMainNav =(BottomNavigationView)findViewById(R.id.main_nav);


        home = new home();
        hesabim =new hesabim();
        ayarlar = new ayarlar();

        setFragment(home);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        mMainNav.setItemBackgroundResource(R.color.design_default_color_primary_dark);
                        setFragment(home);
                        return true;

                    case R.id.hesap:
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(hesabim);
                        return true;

                    case R.id.ayarlar:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(ayarlar);
                        return true;

                    default:
                        return true;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}

