package com.example.quince.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quince.Configuration.RestService.ApiClient;
import com.example.quince.Configuration.RestService.ApiInterface;
import com.example.quince.Domains.Response.UserRegisterRespose;
import com.example.quince.Domains.User.UserAddDTO;
import com.example.quince.R;
import com.example.quince.Service.StorageService;

public class kayit extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        Button kisi = findViewById(R.id.kisi);
        Button ehliyet= findViewById(R.id.ehliyet);


        final EditText username=(EditText)findViewById(R.id.username);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText confPassword=(EditText)findViewById(R.id.confPassword);
        final EditText email=(EditText)findViewById(R.id.mail);


        final Button tamamlandi =(Button)findViewById(R.id.tamamlandi);
        final StorageService storageService =new StorageService();
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        tamamlandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if (email.getText().toString().trim().matches(emailPattern)) {

                if (confPassword.getText().toString().equals(password.getText().toString())) {
                    final UserAddDTO newUser = new UserAddDTO();
                    newUser.setEmail(email.getText().toString());
                    newUser.setUsername(username.getText().toString());
                    newUser.setPassword(password.getText().toString());

                    Call<UserRegisterRespose> registerCall = apiService.register(newUser);
                    registerCall.enqueue(new Callback<UserRegisterRespose>() {
                        @Override
                        public void onResponse(Call<UserRegisterRespose> call, Response<UserRegisterRespose> response) {
                            Toast.makeText(getApplicationContext(), response.body().getInfo(), Toast.LENGTH_SHORT).show();
                            if (response.body().getInfo().equals("User has been created!")) {
                                storageService.storeCreds(newUser.getUsername(), newUser.getPassword(), getApplicationContext());
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegisterRespose> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Sunucu ile iletişimde hata meydana geldi", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
             else {
                 Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
             }
            }
        });



        kisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fKayit();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contain , fragment).commit();

            }
        });

        ehliyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fEhliyet();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contain , fragment).commit();

            }
        });

    }
}



