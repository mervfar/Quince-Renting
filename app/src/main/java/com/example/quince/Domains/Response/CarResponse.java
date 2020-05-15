package com.example.quince.Domains.Response;

import android.content.ClipData;

import com.example.quince.Domains.Car.CarViewDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarResponse {

    @SerializedName("cars")
    @Expose
    private List<CarViewDTO> cars;

    public List<CarViewDTO> getAllCars(){
        return cars;
    }
    public void setItems(List<CarViewDTO> cars){
        this.cars = cars;
    }
}
