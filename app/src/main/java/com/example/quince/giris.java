package com.example.quince;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quince.retrofit.APIService;
import com.example.quince.retrofit.ApiUtils;
import com.example.quince.retrofit.Response4Action;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;


public class giris extends AppCompatActivity {

    Button bntnOfSendRequest;
    String type = "cce";
    private APIService apiService;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        apiService = ApiUtils.getAPIService();
        bntnOfSendRequest = (Button) findViewById(R.id.sendRequest);
        bntnOfSendRequest.setOnClickListener(new View.OnClickListener() {
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

    /*
    public void sendRequest(String user) {
        apiService.savePost(user).enqueue(new Callback<Response4Action>() {

            public void onResponse(Call<Response4Action> call, Response4Action response) {
                if (response.isSuccess()) {
                    Toast.makeText(giris.this, "Başarılı", Toast.LENGTH_SHORT).show();

                }
            }

            public void onFailure(Call<Response4Action> call, Throwable throwable) {
                Toast.makeText(giris.this, "İstek gönderilemedi.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    */
}
