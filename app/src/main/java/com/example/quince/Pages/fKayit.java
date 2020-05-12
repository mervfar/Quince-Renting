package com.example.quince.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quince.Configuration.RestService.ApiClient;
import com.example.quince.Configuration.RestService.ApiInterface;
import com.example.quince.Domains.Response.UserRegisterRespose;
import com.example.quince.Domains.User.UserAddDTO;
import com.example.quince.R;
import com.example.quince.Service.StorageService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fKayit extends Fragment {

    final String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

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
        final EditText name=(EditText)view.findViewById(R.id.name);
        final EditText surname=(EditText)view.findViewById(R.id.surname);
        final EditText telefon=(EditText)view.findViewById(R.id.telefon);


        final Button tamamlandi =(Button)view.findViewById(R.id.tamamlandi2);
        final StorageService storageService =new StorageService();

        tamamlandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().trim().matches(emailPattern)) {

                    if (confPassword.getText().toString().equals(password.getText().toString())) {
                        final UserAddDTO newUser = new UserAddDTO();
                        newUser.setEmail(email.getText().toString());
                        newUser.setUsername(username.getText().toString());
                        newUser.setPassword(password.getText().toString());
                        newUser.setName(name.getText().toString());
                        newUser.setSurname(surname.getText().toString());
                        newUser.setPhoneNumber(telefon.getText().toString());

                        Call<UserRegisterRespose> registerCall = apiService.register(newUser);
                        registerCall.enqueue(new Callback<UserRegisterRespose>() {
                            @Override
                            public void onResponse(Call<UserRegisterRespose> call, Response<UserRegisterRespose> response) {
                                Toast.makeText(getActivity(), response.body().getInfo(), Toast.LENGTH_SHORT).show();
                                if (response.body().getInfo().equals("User has been created!")) {
                                    storageService.storeCreds(newUser.getUsername(), newUser.getPassword(), getActivity());
                                }
                            }

                            @Override
                            public void onFailure(Call<UserRegisterRespose> call, Throwable t) {
                                Toast.makeText(getActivity(), "Sunucu ile iletişimde hata meydana geldi", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
        }
    }

