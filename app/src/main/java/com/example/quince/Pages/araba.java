package com.example.quince.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.quince.Configuration.RestService.ApiClient;
import com.example.quince.Configuration.RestService.ApiInterface;
import com.example.quince.Domains.Car.CarViewDTO;
import com.example.quince.Domains.Response.CarResponse;
import com.example.quince.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class araba extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView marka1;
    //TextView fiyat;
    TextView gear;
    TextView fuel;
    TextView year;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba);

        initViews();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(araba.this, "Github Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }
    private void loadJSON(){
        marka1 = (TextView) findViewById(R.id.marka1);
        //fiyat = (TextView) findViewById(R.id.fiyat);
        gear = (TextView) findViewById(R.id.gear);
        //fuel = (TextView) findViewById(R.id.fuel);
        year = (TextView) findViewById(R.id.year);
        try{
            final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<CarResponse> call = apiService.getAllCars();
            call.enqueue(new Callback<CarResponse>() {
                @Override
                public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                    List<CarViewDTO> cars = response.body().getAllCars();
                    recyclerView.setAdapter(new ArabaAdapter(getApplicationContext(), cars));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }
                @Override
                public void onFailure(Call<CarResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(araba.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    marka1.setVisibility(View.VISIBLE);
                    //fiyat.setVisibility(View.VISIBLE);
                    gear.setVisibility(View.VISIBLE);
                    //fuel.setVisibility(View.VISIBLE);
                    year.setVisibility(View.VISIBLE);
                    pd.hide();
                }

            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
