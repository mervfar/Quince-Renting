package com.example.quince.Pages;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quince.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class ArabaAdapter extends RecyclerView.Adapter<ArabaAdapter.ViewHolder>{

    private List<ClipData.Item> items;
    private Context context;

    public ArabaAdapter(Context applicationContext, List<ClipData.Item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public ArabaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArabaAdapter.ViewHolder viewHolder, int i) {
        viewHolder.marka.setText(items.get(i).getLogin()); //api deki getter setter metotları
        viewHolder.fiyat.setText(items.get(i).getHtmlUrl());
        viewHolder.gear.setText(items.get(i).getLogin());
        viewHolder.fuel.setText(items.get(i).getHtmlUrl());
        viewHolder.year.setText(items.get(i).getLogin());


        Picasso.with(context)
                .load(items.get(i).getAvatarUrl())  //api fotograf metotu
                .placeholder(R.drawable.ic_refresh_black_24dp)
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView marka;
        TextView fiyat;
        TextView gear;
        TextView fuel;
        TextView year;


        public ViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.image);
            marka = (TextView) view.findViewById(R.id.marka);
            fiyat = (TextView) view.findViewById(R.id.fiyat);
            gear = (TextView) view.findViewById(R.id.gear);
            fuel = (TextView) view.findViewById(R.id.fuel);
            year = (TextView) view.findViewById(R.id.year);


            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        ClipData.Item clickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, araba2.class);
                        intent.putExtra("marka", items.get(pos).getLogin());
                        intent.putExtra("fiyat", items.get(pos).getHtmlUrl());
                        intent.putExtra("gear", items.get(pos).getAvatarUrl());
                        intent.putExtra("fuel", items.get(pos).getHtmlUrl());
                        intent.putExtra("year", items.get(pos).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
}
