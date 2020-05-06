package com.example.quince.Pages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quince.Configuration.RestService.ApiClient;
import com.example.quince.Configuration.RestService.ApiInterface;
import com.example.quince.Domains.Response.UserRegisterRespose;
import com.example.quince.Domains.User.UserAddDTO;
import com.example.quince.R;
import com.example.quince.Service.StorageService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fKayit extends Fragment {


    Button tamamlandi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final View view = inflater.inflate(R.layout.fragment_kisibilgileri, container, false);

        final EditText username=(EditText)view.findViewById(R.id.username);
        final EditText password=(EditText)view.findViewById(R.id.password);
        final EditText confPassword=(EditText)view.findViewById(R.id.confPassword);
        final EditText email=(EditText)view.findViewById(R.id.mail);


        final Button registerAction =(Button)view.findViewById(R.id.tamamlandi);
        final StorageService storageService =new StorageService();

        registerAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confPassword.getText().toString().equals(password.getText().toString())){
                    final UserAddDTO newUser=new UserAddDTO();
                    newUser.setEmail(email.getText().toString());
                    newUser.setUsername(username.getText().toString());
                    newUser.setPassword(password.getText().toString());
                    Call<UserRegisterRespose> registerCall=apiService.register(newUser);
                    registerCall.enqueue(new Callback<UserRegisterRespose>() {
                        @Override
                        public void onResponse(Call<UserRegisterRespose> call, Response<UserRegisterRespose> response) {
                            Toast.makeText(getActivity().getApplicationContext(), response.body().getInfo(), Toast.LENGTH_SHORT).show();
                            if (response.body().getInfo().equals("Kişi bilgileriniz kaydedildi. Lütfen ehliyet bilgilerine geçiniz.")){
                                storageService.storeCreds(newUser.getUsername(),newUser.getPassword(),getActivity().getApplicationContext());
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegisterRespose> call, Throwable t) {
                            Toast.makeText(getActivity().getApplicationContext(), ",Sunucu ile iletişimde hata meydana geldi", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        return view;
        }
    }

