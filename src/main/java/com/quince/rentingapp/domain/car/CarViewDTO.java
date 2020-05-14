package com.quince.rentingapp.domain.car;

import lombok.Data;

@Data
public class CarViewDTO {
    private Long id;
    private String name;
    private Double engineSize;
    private String colorCode;
    private String brand;
    private String series;
    private String price;
    private double year;
    private String fuel;
    private String carBody;
    private String gear;
    private String imageUrl;
    private String gltfUrl;
    private boolean available;

}
