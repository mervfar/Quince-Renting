package com.quince.rentingapp.domain.car;

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
