package com.quince.rentingapp.domain.car;

import lombok.Data;

@Data
public class CarViewDTO {
    private Long id;
    private String name;
    private Double engineSize;
    private String colorCode;
    private CarBrand brand;
    private String series;
    private double year;
    private CarFuelType fuel;
    private CarBody carBody;
    private CarTransmissionType gear;
    private String imageUrl;
    private String gltfUrl;
    private boolean available;

}
