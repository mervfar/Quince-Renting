package com.example.quince;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fKayit extends Fragment {


    Button tamamlandi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kisibilgileri, container, false);

        tamamlandi = (Button) view.findViewById(R.id.tamamlandi);
        tamamlandi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Kişi bilgileriniz kaydedildi. Lütfen ehliyet bilgilerine geçiniz.", Toast.LENGTH_SHORT).show();

                RestInterface api = new RestInterface();
                api.GetToken("/oauth/token?username=mervfar&password=12345&grant_type=password" ,getActivity());
                api.execute();

            }
        });

        return view;
        }
    }

