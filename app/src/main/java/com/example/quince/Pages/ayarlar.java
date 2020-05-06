package com.example.quince.Pages;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quince.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ayarlar extends Fragment {


    public ayarlar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ayarlar, container, false);
        return view;
    }

}
