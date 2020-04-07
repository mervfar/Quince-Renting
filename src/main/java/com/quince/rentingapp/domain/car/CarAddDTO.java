package com.quince.rentingapp.domain.car;

import lombok.Data;

@Data
public class CarAddDTO{
    private String name;
    private Double engineSize;
    private String colorCode;
    private CarBrand brand;
    private String series;
    private double year;
    private CarFuelType fuel;
    private CarBody carBody;
    private CarTransmissionType gear;
}