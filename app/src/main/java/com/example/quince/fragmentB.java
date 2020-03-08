package com.example.quince;

import android.app.FragmentBreadCrumbs;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class fragmentB extends Fragment {
    EditText pass, confPass, mail;
    Button uye_Ol;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        pass = (EditText) view.findViewById(R.id.password);
        confPass = (EditText) view.findViewById(R.id.confPassword);
        mail=(EditText)view.findViewById(R.id.mail);
        uye_Ol = (Button) view.findViewById(R.id.uyeOl);
        uye_Ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password =pass.getText().toString();
                String confirm =confPass.getText().toString();
                String email=mail.getText().toString();



                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity().getApplicationContext(), "Lütfen email giriniz.",Toast.LENGTH_SHORT).show();}

                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity().getApplicationContext(), "Lütfen şifrenizi giriniz.", Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(confirm)){
                    Toast.makeText(getActivity().getApplicationContext(), "Lütfen şifrenizi doğrulayınız.", Toast.LENGTH_SHORT).show();}
                else if(password != confirm){
                    Toast.makeText(getActivity().getApplicationContext(), "Şifreler eşleşmiyor!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Kayıt işlemi gerçekleştirildi.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }
    }
