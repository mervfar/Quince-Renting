package com.example.quince.Pages;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quince.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class hesabim extends Fragment {

    Button kayit;
    TextView isim;
    TextView soyisim;
    TextView mail;
    public hesabim() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hesabim, container, false);


        isim=(TextView)view.findViewById(R.id.isim);
        soyisim=(TextView)view.findViewById(R.id.soyisim);
        mail=(TextView)view.findViewById(R.id.mail);

        kayit = (Button) view.findViewById(R.id.kayit);
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),kayit.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
