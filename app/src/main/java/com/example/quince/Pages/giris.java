package com.example.quince.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quince.Configuration.RestService.ApiClient;
import com.example.quince.Configuration.RestService.ApiInterface;
import com.example.quince.Domains.Token.Secret;
import com.example.quince.Domains.Token.Token;
import com.example.quince.R;
import com.example.quince.Service.StorageService;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class giris extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Secret secret = new Secret();
        final StorageService storageService = new StorageService();


        Button getToken = (Button) findViewById(R.id.giris);
        getToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> creds = storageService.getCreds(getApplicationContext());
                Call<Token> tokenCall= apiService.getToken(creds.get("username"),creds.get("password"),"password",secret.getSecretBase64());
                tokenCall.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if(response.isSuccessful()){
                            System.out.println("TOKENNNN"+response.body().getAccess_token());
                            storageService.storeToken(response.body().getAccess_token(),getApplicationContext());
                        }
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        System.out.println("###########"+t.toString());

                    }
                });
                Intent intent = new Intent(getApplicationContext(), anaSayfa.class);
                startActivity(intent);
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
