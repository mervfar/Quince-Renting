package com.example.quince.Domains.Car;

import lombok.Data;
@Data
public class CarSearchQuery {

    private String brand;
    private String pickup_location;
    private String pickup_date;
    private String dropoff_date;
    private String dropoff_location;
    private String country_code;

}
