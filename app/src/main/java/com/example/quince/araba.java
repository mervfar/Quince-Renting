package com.example.quince;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class araba extends AppCompatActivity {
    ListView listView;
    int images[] = {R.drawable.ic_access_time_black_24dp ,R.drawable.ic_access_time_black_24dp,R.drawable.ic_access_time_black_24dp,R.drawable.ic_access_time_black_24dp,R.drawable.ic_access_time_black_24dp};
    String mMarka[] = {"M1", "M2", "M3", "M4", "M5"};
    String mFiyat[] = {"0TL", "0TL", "0TL", "0TL", "0TL"};
    String mGear[] = {"düz","düz" ,"düz","düz","düz"};
    String mFuel[] = {"km","km" ,"km","km","km"};
    String mYear[] = {"2020","2020" ,"2020","2020","2020"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba);
        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mMarka, mFiyat, mGear ,mFuel ,mYear , images);
        listView.setAdapter(adapter);

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(araba.this, araba2.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[0]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("marka", mMarka[0]);
                    intent.putExtra("fiyat", mFiyat[0]);
                    intent.putExtra("gear", mGear[0]);
                    intent.putExtra("fuel", mFuel[0]);
                    intent.putExtra("year", mYear[0]);

                    // also put your position
                    intent.putExtra("position", ""+0);
                    startActivity(intent);


                }
                if (position == 1) {
                    Intent intent = new Intent(araba.this, araba2.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[1]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("marka", mMarka[1]);
                    intent.putExtra("fiyat", mFiyat[1]);
                    intent.putExtra("gear", mGear[1]);
                    intent.putExtra("fuel", mFuel[1]);
                    intent.putExtra("year", mYear[1]);
                    // also put your position
                    intent.putExtra("position", ""+1);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(araba.this, araba2.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[2]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("marka", mMarka[2]);
                    intent.putExtra("fiyat", mFiyat[2]);
                    intent.putExtra("gear", mGear[2]);
                    intent.putExtra("fuel", mFuel[2]);
                    intent.putExtra("year", mYear[2]);
                    // also put your position
                    intent.putExtra("position", ""+2);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(araba.this, araba2.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[3]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("marka", mMarka[3]);
                    intent.putExtra("fiyat", mFiyat[3]);
                    intent.putExtra("gear", mGear[3]);
                    intent.putExtra("fuel", mFuel[3]);
                    intent.putExtra("year", mYear[3]);
                    // also put your position
                    intent.putExtra("position", ""+3);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(araba.this, araba2.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[4]);
                    intent.putExtras(bundle);

                    intent.putExtra("marka", mMarka[4]);
                    intent.putExtra("fiyat", mFiyat[4]);
                    intent.putExtra("gear", mGear[4]);
                    intent.putExtra("fuel", mFuel[4]);
                    intent.putExtra("year", mYear[4]);
                    // also put your position
                    intent.putExtra("position", ""+4);
                    startActivity(intent);
                }
            }
        });
    }


    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rMarka[];
        String rFiyat[];
        String rGear[];
        String rFuel[];
        String rYear[];
        int rImgs[];

        MyAdapter(Context c, String marka[], String fiyat[], String gear[],String fuel[],String year[],int imgs[]) {
            super(c, R.layout.row, R.id.marka1, marka);
            this.context = c;
            this.rMarka = marka;
            this.rFiyat = fiyat;
            this.rGear = gear;
            this.rFuel = fuel;
            this.rYear = year;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView marka = row.findViewById(R.id.marka1);
            TextView fiyat = row.findViewById(R.id.fiyat);
            TextView gear = row.findViewById(R.id.gear);
            TextView fuel = row.findViewById(R.id.fuel);
            TextView year = row.findViewById(R.id.year);

            images.setImageResource(rImgs[position]);
            marka.setText(rMarka[position]);
            fiyat.setText(rFiyat[position]);
            gear.setText(rGear[position]);
            fuel.setText(rFuel[position]);
            year.setText(rYear[position]);

            return row;
        }

    }
}
