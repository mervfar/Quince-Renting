package com.example.quince.Domains.Car;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
//getter setter yapar.

@Data
public class CarViewDTO {

    @SerializedName("name")
    private String name;

    @SerializedName("engineSize")
    private Double engineSize;

    @SerializedName("brand")
    private String brand;

    @SerializedName("series")
    private String series;

    @SerializedName("year")
    private String year;

    @SerializedName("fuel")
    private String fuel;

    @SerializedName("carBody")
    private String  carBody;

    @SerializedName("gear")
    private String gear;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("gltfUrl")
    private String gltfUrl;

}
