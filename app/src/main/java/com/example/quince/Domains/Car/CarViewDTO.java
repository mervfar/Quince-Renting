package com.example.quince.Domains.Car;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
//getter setter yapar.

@Data
public class CarViewDTO {

    @SerializedName("name")
    private String name;

    @SerializedName("brand")
    private String brand;

    @SerializedName("series")
    private String series;

    @SerializedName("price")
    private String price;

    @SerializedName("carBody")
    private String  carBody;

    @SerializedName("gear")
    private String gear;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("gltfUrl")
    private String gltfUrl;

}
