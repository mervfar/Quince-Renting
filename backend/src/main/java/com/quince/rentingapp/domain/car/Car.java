package com.quince.rentingapp.domain.car;

import com.quince.rentingapp.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
@Entity
@Data
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CarSeq")
@Table(name = "Cars")
public class Car extends BaseEntity {

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


}
