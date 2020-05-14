package com.quince.rentingapp.domain.car;

import lombok.Data;

@Data
public class CarAddDTO{
    private String name;
    private Double engineSize;
    private String colorCode;
    private String brand;
    private String series;
    private double year;
    private String fuel;
    private String carBody;
    private String gear;
}