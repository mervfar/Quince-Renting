package com.example.quince.Pages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quince.Domains.Car.CarViewDTO;
import com.example.quince.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ArabaAdapter extends RecyclerView.Adapter<ArabaAdapter.ViewHolder> {

    private List<CarViewDTO> cars;
    private Context context;

    public ArabaAdapter(Context applicationContext, List<CarViewDTO> carViewDTOList) {
        this.context = applicationContext;
        this.cars =  carViewDTOList;
    }

    @Override
    public ArabaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.marka.setText(cars.get(i).getBrand()); //api deki getter setter metotları
        viewHolder.fiyat.setText(cars.get(i).getPrice());
        viewHolder.gear.setText(cars.get(i).getGear());
        viewHolder.carbody.setText(cars.get(i).getCarBody());
        viewHolder.series.setText(cars.get(i).getSeries());

        Glide.with(context)
                .load(cars.get(i).getImageUrl())  //api fotograf metotu
                .placeholder(R.drawable.ic_refresh_black_24dp)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView marka;
        TextView fiyat;
        TextView gear;
        TextView carbody;
        TextView series;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            marka = (TextView) view.findViewById(R.id.marka);
            fiyat = (TextView) view.findViewById(R.id.fiyat);
            gear = (TextView) view.findViewById(R.id.gear);
            carbody = (TextView) view.findViewById(R.id.carBody);
            series = (TextView) view.findViewById(R.id.series);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        CarViewDTO clickedDataItem = cars.get(pos);
                        Intent intent = new Intent(context, araba2.class);
                        intent.putExtra("marka", cars.get(pos).getBrand());
                        intent.putExtra("fiyat", cars.get(pos).getPrice());
                        intent.putExtra("gear", cars.get(pos).getGear());
                        intent.putExtra("carbody", cars.get(pos).getCarBody());
                        intent.putExtra("gear", cars.get(pos).getSeries());
                        intent.putExtra("image", cars.get(pos).getImageUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getBrand(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}