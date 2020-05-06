package com.example.quince.Domains.Car;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Setter;

@Data //getter setter yapar.

public class CarViewDTO {

    @SerializedName("name")
    private String name;

    @SerializedName("engineSize")
    private Double engineSize;

    @SerializedName("colorCode")
    private String colorCode;

    @SerializedName("brand")
    private CarBrand brand;

    @SerializedName("series")
    private String series;

    @SerializedName("year")
    private double year;

    @SerializedName("fuel")
    private CarFuelType fuel;

    @SerializedName("carBody")
    private CarBody carBody;

    @SerializedName("gear")
    private CarTransmissionType gear;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("gltfUrl")
    private String gltfUrl;

    @SerializedName("available")
    private boolean available;
}
