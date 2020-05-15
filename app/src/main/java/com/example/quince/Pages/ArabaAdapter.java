package com.example.quince.Pages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.quince.Domains.Car.CarViewDTO;
import com.example.quince.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ArabaAdapter extends RecyclerView.Adapter<ArabaAdapter.ViewHolder> {

    private CarViewDTO cars;
    private Context context;

    public ArabaAdapter(Context applicationContext, List<CarViewDTO> itemArrayList) {
        this.context = applicationContext;
        this.cars = (CarViewDTO) itemArrayList;
    }

    @Override
    public ArabaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.marka.setText(cars.getBrand()); //api deki getter setter metotları
        //viewHolder.fiyat.setText(cars.get(i).);
        viewHolder.gear.setText(cars.getGear());
        //viewHolder.fuel.setText(cars.getFuel());
        viewHolder.year.setText(String.valueOf(cars.getYear()));
        Picasso.with(context)
                .load(cars.getImageUrl())  //api fotograf metotu
                .placeholder(R.drawable.ic_refresh_black_24dp)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return 0;  //cars.size()
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView marka;
        //TextView fiyat;
        TextView gear;
        //TextView fuel;
        TextView year;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            marka = (TextView) view.findViewById(R.id.marka);
            //fiyat = (TextView) view.findViewById(R.id.fiyat);
            gear = (TextView) view.findViewById(R.id.gear);
           // fuel = (TextView) view.findViewById(R.id.fuel);
            year = (TextView) view.findViewById(R.id.year);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        //CarViewDTO clickedDataItem = cars.get(pos);
                        Intent intent = new Intent(context, araba2.class);
                        intent.putExtra("marka", cars.getBrand());
                        //intent.putExtra("fiyat", cars.get(pos).);
                        intent.putExtra("gear", cars.getGear());
                       // intent.putExtra("fuel", cars.getFuel());
                        intent.putExtra("year", cars.getYear());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        //Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getBrand(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}