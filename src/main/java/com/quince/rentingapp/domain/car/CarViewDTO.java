package com.quince.rentingapp.domain.car;

import lombok.Data;

@Data
public class CarViewDTO {
    private String name;
    private String brand;
    private String series;
    private String price;
    private String fuel;
    private String carBody;
    private String gear;
    private String imageUrl;
    private String gltfUrl;

}
