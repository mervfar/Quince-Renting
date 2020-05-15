package com.example.quince.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.quince.Configuration.RestService.ApiClient;
import com.example.quince.Configuration.RestService.ApiInterface;
import com.example.quince.Domains.Car.CarSearchQuery;
import com.example.quince.Domains.Car.CarViewDTO;
import com.example.quince.R;
import com.example.quince.Service.StorageService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class araba extends AppCompatActivity {
    private RecyclerView recyclerView;

    TextView marka1;
    TextView fiyat;
    TextView gear;
    TextView carbody;
    TextView series;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    final StorageService storageService = new StorageService();

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
        pd.setMessage("Arabalar yükleniyor...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }
    private void loadJSON(){
        marka1 = (TextView) findViewById(R.id.marka);
        fiyat = (TextView) findViewById(R.id.fiyat);
        gear = (TextView) findViewById(R.id.gear);
        carbody = (TextView) findViewById(R.id.carBody);
        series = (TextView) findViewById(R.id.series);

        try{
            final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            CarSearchQuery carSearchQuery =new CarSearchQuery();
            carSearchQuery.setBrand("Avis");
            carSearchQuery.setCountry_code("GB");
            carSearchQuery.setDropoff_date("2020-05-18T20:22:02");
            carSearchQuery.setDropoff_location("K9N");
            carSearchQuery.setPickup_date("2020-05-18T10:22:02");
            carSearchQuery.setPickup_location("K9N");
            Call<List<CarViewDTO>> call = apiService.getAllCars(carSearchQuery,storageService.gettoken(getApplicationContext()));
            call.enqueue(new Callback<List<CarViewDTO>>() {
                @Override
                public void onResponse(Call<List<CarViewDTO>> call, Response<List<CarViewDTO>> response) {
                    List<CarViewDTO> cars = response.body();
                    recyclerView.setAdapter(new ArabaAdapter(getApplicationContext(), cars));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }
                @Override
                public void onFailure(Call<List<CarViewDTO>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(araba.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    marka1.setVisibility(View.VISIBLE);
                    fiyat.setVisibility(View.VISIBLE);
                    gear.setVisibility(View.VISIBLE);
                    carbody.setVisibility(View.VISIBLE);
                    series.setVisibility(View.VISIBLE);
                    pd.hide();
                }

            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
